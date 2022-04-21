/*
 * FileName: DealSqlite
 * Author:   reedsource
 */
package top.ireed.found;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import top.ireed.deal.DealDate;
import top.ireed.deal.DealLog;
import top.ireed.deal.DealObject;
import top.ireed.entity.PageData;
import top.ireed.entity.PageTime;
import top.ireed.general.TopException;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.Date;
import java.util.*;

/**
 * 功能简述:
 * 〈sqlite工具类 1.0〉
 * 工具默认全约定模式 保留sql语句能力
 * <p>
 * 引入hutool处理
 * 引入jackson处理
 *
 * @author reedsource
 * @version 1.0.0
 * date 2020/5/29 10:39
 * reedsource@189.cn
 */
public class FoundSqlite {

	/**
	 * 查询条数常量
	 */
	private static final String COUNT = "SELECT count(1) FROM ";

	/**
	 * FoundSqlite初始化及日志表
	 * <p>
	 * 1 创建时间
	 * 当第一次运行初始化时 创建本表
	 * 2. 作用
	 * 当创建本表之后,在sqlite默认的表sqlite_sequence系统表中将会记录其它表的RowID最大值
	 * 本工具将会根据系统表中能否查询到数据来判断初始连接是否正常
	 * <p>
	 * 另外,本工具将会把数据操作日志保存在本表中
	 */
	private static final String CREATE_FOUND_SQLITE_LOG = "CREATE TABLE IF NOT EXISTS FoundSqliteLog(id integer PRIMARY KEY autoincrement,date text,key text,value text,result text)";

	/**
	 * 查询日志表数据条数
	 */
	private static final String SQLITE_LOG_SUM = "select count(1) from FoundSqliteLog";
	private static final String ERR = "非约定模式,无法使用本方式";

	/**
	 * sqlite关键字,当建表表名为这些字段时,会出现异常
	 */
	private final String[] sqliteFinalArray = {"order"};

	/**
	 * sqlData url
	 */
	private final String sqliteDataUrl;
	/**
	 * SQLite连接对象
	 */
	private static Connection conn = null;
	/**
	 * 数据库查询对象
	 */
	private static Statement stmt = null;


	/**
	 * 约定模式
	 * <p>
	 * 数据库模式 分为 手动false 约定true
	 * 默认为约定模式 约定模式下,一般需要项目开始就使用约定模式 表名等使用约定命名方案
	 * 当前约定表名称命名方案为 实体名称+Top
	 * <p>
	 * 手动模式适合兼容原数据库,或者并不是在约定模式下创建的表
	 */
	private final boolean pattern;

	//===数据库构建方法====================================================================

	/**
	 * 约定模式 生成一个sqlite工具方法
	 *
	 * @param sqliteDataUrl 数据库路径url
	 */
	public FoundSqlite(String sqliteDataUrl) throws TopException {
		this(sqliteDataUrl, true);
	}

	/**
	 * 生成一个sqlite工具方法
	 *
	 * @param sqliteDataUrl 数据库路径url
	 * @param pat           约定模式 true约定
	 */
	public FoundSqlite(String sqliteDataUrl, Boolean pat) throws TopException {
		this.sqliteDataUrl = sqliteDataUrl;
		this.pattern = pat;

		//1.1 判断数据库文件夹是否存在,不存在创建
		//不存在且创建失败
		if (!mkdirFile()) {
			DealLog.log(sqliteDataUrl, "数据库依赖路径创建失败");
		}

		//1.2 尝试创建连接数据库文件  本处,数据库文件不存在,会自动创建db数据库空文件
		getSqLite(sqliteDataUrl);

		//1.3 判断初始化工具日志表
		//如果系统数据表数据条数小于0 表不存在 尝试添加
		if (getSum(SQLITE_LOG_SUM, false) < 0) {
			//建表语句正确无返回 表创建完成 连接已经正常
			if (set(CREATE_FOUND_SQLITE_LOG, "") == 0) {
				insertLog("数据库初始化", "创建日志表");
			}
		}
		DealLog.log("数据库连接成功", sqliteDataUrl);
		insertLog("连接", sqliteDataUrl, "成功");
	}

	//===数据库初始化====================================================================

	/**
	 * 约定模式 数据库表初始化
	 * 约定表名 实体类名称+Top
	 * 约定主键 id
	 * 约定主键自增 true
	 *
	 * @param o 初始化实体类
	 * @return 初始化是否成功
	 */
	public boolean init(Object o) {
		return init(getTableName(o.getClass()), o, "id", true);
	}

	/**
	 * 数据库表初始化
	 *
	 * @param tableName 操作表名
	 * @param object    表元素实体类
	 * @param keyName   主键名,必须在表元素实体类中
	 * @param increase  主键是否自增,默认不自增  自增true 不自增 false
	 * @return 是否正常走初始化流程 初始化完成放回true 其他 表已经存在,初始化异常 返回false
	 */
	public boolean init(String tableName, Object object, String keyName, boolean increase) {

		//2 .判断表存在
		//2.1 将表名转化为全小写后 表名白名单校验
		if (Arrays.asList(sqliteFinalArray).contains(tableName.toLowerCase())) {
			DealLog.log(tableName, "表名称为sqlite关键字,无法用于建表表名");
			return false;
		}
		//2.2 表存在正常,返回true
		if (foundTable(tableName).equals(true)) {
			DealLog.log(tableName, "正常");
			return false;
		}

		//2.2 开始表初始化流程
		DealLog.log(tableName, "表存在异常,尝试初始化表");
		//2.2.1 判断主键存在
		if (Boolean.FALSE.equals(DealObject.isExistFieldName(object, keyName))) {
			DealLog.log(tableName, "表主键" + keyName + "在表元素实体类中不存在");
			return false;
		}
		//2.2.2获取组装的建表语句
		String tableSql = assemble(object, tableName, keyName, increase);
		//2.2.3 尝试创建表
		try {
			stmt.executeUpdate(tableSql);
			insertLog("表初始化成功", "表语句" + tableSql);
		} catch (SQLException e1) {
			insertLog("表初始化失败", "表语句" + tableSql);
			DealLog.log(tableName, tableSql + " 创建失败, 请检查实体类" + e1);
			return false;
		}
		DealLog.log(tableName, "初始化完成");
		return true;
	}

	//===数据库工具类====================================================================

	/**
	 * 连接数据库  连接成功 赋值公共数据库连接对象
	 *
	 * @param url 数据库地址
	 */
	private static void getSqLite(String url) throws TopException {
		try {
			//SQLite连接对象
			conn = DriverManager.getConnection(url);
			//连接查询对象
			stmt = conn.createStatement();
		} catch (SQLException e) {
			DealLog.log("数据库连接失败 " + e);
			throw new TopException("数据库连接失败", e);
		}
	}

	/**
	 * 关闭资源
	 */
	public void close() throws TopException {
		//关闭资源的顺序为,按照创建的顺序逆序关闭
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			throw new TopException("数据库资源关闭失败", e);
		}
	}

	/**
	 * 尝试校验创建数据库路径
	 *
	 * @return 校验路径是否正常
	 */
	private boolean mkdirFile() {
		File baFile = new File(new File(StrUtil.replaceIgnoreCase(sqliteDataUrl, "jdbc:sqLite:", "")).getParent());
		//判断是否存在
		if (!baFile.exists()) {
			//不存在尝试创建
			return baFile.mkdirs();
		}
		return true;
	}

	/**
	 * 通过表名确定表存在
	 * <p>
	 * 当表不存在,返回false
	 *
	 * @param tableName 表名
	 * @return 表存在
	 */
	public Boolean foundTable(String tableName) {
		return getSum(COUNT + tableName, false) != -1;
	}

	/**
	 * 约定模式 通过实体确定表存在
	 * <p>
	 * 当表不存在,返回false
	 *
	 * @param o 表实体
	 * @return 表存在
	 */
	public Boolean foundTable(Object o) {
		return getSum(COUNT + getTableName(o), false) != -1;
	}


	/**
	 * 根据是否约定模式 返回表名
	 *
	 * @param o 类
	 * @return 表名
	 */
	private String getTableName(Object o, String tableName) {
		return pattern ? getTableName(o) : tableName;
	}

	/**
	 * 生成约定表名
	 *
	 * @param o 类
	 * @return 约定表名 实体类名称+Top
	 */
	private String getTableName(Object o) {
		String c = o.getClass().getName();
		//截取class.最后一次出现位置之后的文本
		return getTableName(c);
	}

	/**
	 * 生成约定表名
	 *
	 * @param t class
	 * @return 约定表名 实体类名称+Top
	 */
	private String getTableName(Class<?> t) {
		String c = t.getName();
		//截取class.最后一次出现位置之后的文本
		return getTableName(c);
	}

	/**
	 * 生成约定表名
	 *
	 * @param c class名称类型文件名称
	 * @return 约定表名 实体类名称+Top
	 */
	private String getTableName(String c) {
		//截取class.最后一次出现位置之后的文本 . ASCII码 为 46
		return c.substring(c.lastIndexOf(46) + 1) + "Top";
	}

	//===语句组装====================================================================

	/**
	 * 组装添加语句
	 * <p>
	 * 本处不对key做校验
	 * 当工具类主键为自增时
	 * 插入表主键为自增时将抛出错误
	 *
	 * @param tableName 表名 不考虑库操作
	 * @param o         实体数据
	 * @return 数据
	 */
	private String getInsertSqliteStr(String tableName, Object o) throws TopException {
		//1.表名 不考虑库操作
		tableName = getTableName(o, tableName);
		//2.实体数据
		// 添加类型 key , key , key
		StringBuilder b = new StringBuilder();
		// 尾数据类型 key = 'value' ,/AND key = 'value'
		StringBuilder c = new StringBuilder();
		Field[] fields = o.getClass().getDeclaredFields();
		//是否添加过有效字段标记 初始false 当第一次添加有用数据字段后,后续在前面添加,分隔
		boolean si = false;
		for (Field field : fields) {
			//将实体数据全部转化为字符串类型
			String m = DealObject.getMember(o, field.getName());
			//查询的数据不为空且不为null
			if (!Objects.equals(m, "")) {
				//添加 特殊形态  value , value , value
				//本处如果实体存在主键,也会参与主键拼接 需要注意
				//如果添加过有数据字段
				if (si) {
					b.append(",");
					c.append(",");
				}
				b.append(field.getName());
				c.append("'");
				if (field.getType().isAssignableFrom(Date.class)) {
					c.append(DealDate.getSqliteDate(DealDate.getDate(m)));
				} else {
					c.append(m);
				}
				c.append("'");
				si = true;
			}
		}
		//3. 组装主体语句
		//1.1添加  "INSERT INTO tableName (key,value) VALUES('数据1','mm1')"
		return "INSERT INTO " + tableName + "(" + b.toString() + ") VALUES(" + c.toString() + ")";
	}

	/**
	 * 更新语句组装
	 *
	 * @param tableName 表名 不考虑库操作
	 * @param o         实体数据
	 * @param keyName   主键名称
	 * @return 语句
	 */
	private String getUpdateSqliteStr(String tableName, Object o, String keyName) throws TopException {
		//1.表名 不考虑库操作
		tableName = getTableName(o, tableName);
		//2.实体数据
		// 尾数据类型 key = 'value' ,/AND key = 'value'
		StringBuilder c = new StringBuilder();
		Field[] fields = o.getClass().getDeclaredFields();
		//主键标记 当主键存在,记录主键值是否已经记录
		String key = null;
		//是否添加过有效字段标记 初始false 当第一次添加有用数据字段后,后续在前面添加,分隔
		boolean si = false;
		for (Field field : fields) {
			//将实体数据全部转化为字符串类型
			String m = DealObject.getMember(o, field.getName());
			//查询的数据不为空且不为null
			//查询屏蔽默认创建时间
			if (!Objects.equals(m, "") && !("createTime".equals(field.getName()))) {
				//主键不参与条件拼接
				//更新实体主键存在状态
				if (field.getName().equals(keyName)) {
					key = m;
				} else {
					//拼接 key = 'value' , key = 'value' 数据
					//如果添加过有数据字段
					if (si) {
						c.append(" , ");
					}
					c.append(field.getName()).append(" = '").append(m).append("' ");
					si = true;
				}
			}
		}
		//3. 组装主体语句

		//1 判断主键是否存在数据
		if (key == null) {
			throw new TopException("主键" + keyName + "在" + o, "不存在");
		}
		//时间条件
		return "UPDATE " + tableName + " SET " + c.toString() + "WHERE " + keyName + " = " + key;
	}


	/**
	 * 查询语句组装
	 *
	 * @param tableName 表名 不考虑库操作
	 * @param o         实体数据
	 * @return 语句
	 */
	private String getSqliteStr(boolean delete, String tableName, Object o, List<PageTime> list) throws TopException {
		//1.表名 不考虑库操作
		tableName = getTableName(o, tableName);
		//2.实体数据
		// 尾数据类型 key = 'value' ,/AND key = 'value'
		StringBuilder c = new StringBuilder();
		Field[] fields = o.getClass().getDeclaredFields();
		//是否添加过有效字段标记 初始false 当第一次添加有用数据字段后,后续在前面添加,分隔
		boolean si = false;
		for (Field field : fields) {
			//将实体数据全部转化为字符串类型
			String m = DealObject.getMember(o, field.getName());
			//查询的数据不为空且不为null
			//查询屏蔽默认创建时间

			//默认不是查询条件
			boolean notTime = true;
			//排除查询条件
			if (list != null) {
				for (PageTime pageTime : list) {
					if (pageTime.getDateName().equals(field.getName())) {
						notTime = false;
						break;
					}
				}
			}
			if (!Objects.equals(m, "") && notTime) {
				//拼接 key = 'value' , key = 'value' 数据
				//如果添加过有数据字段
				if (si) {
					c.append(" AND ");
				}
				c.append(field.getName()).append(" = '").append(m).append("' ");
				si = true;
			}
		}
		//3. 组装主体语句
		StringBuilder a = new StringBuilder();

		//删除数据
		if (delete) {
			if (StrUtil.isNotBlank(c)) {
				a.append("DELETE FROM ").append(tableName).append(" WHERE ").append(c.toString());
			} else {
				throw new TopException("删除语句条件不存在", o);
			}
		} else {
			a.append("SELECT * FROM ").append(tableName);
			//添加过有效查询字段
			if (si) {
				a.append(" WHERE ");
			}
			a.append(c.toString());
		}
		//时间条件
		return orDateStr(a, list, si).toString();
	}

	/**
	 * @param a    组装主体语句
	 * @param list 查询多条件时间
	 * @param si   是否添加过有效字段标记
	 * @return 语句
	 */
	private StringBuilder orDateStr(StringBuilder a, List<PageTime> list, boolean si) {
		if (list != null) {
			for (PageTime pageTime : list) {
				String name = pageTime.getDateName();
				if (pageTime.getBeginDate() != null) {
					a.append(si ? " AND " + name + " >= '" : " WHERE " + name + " >= '").append(DealDate.getSqliteDate(pageTime.getBeginDate())).append("'");
					//已经添加数据
					si = true;
				}

				if (pageTime.getEndDate() != null) {
					a.append(si ? " AND " + name + " < '" : " WHERE " + name + " < '").append(DealDate.getSqliteDate(pageTime.getEndDate())).append("'");

				}
			}
		}
		return a;
	}


	/**
	 * 组装根据实体建表语句
	 *
	 * @param object    实体
	 * @param tableName 操作表名
	 * @param keyName   主键名称
	 * @return 组装sql
	 */
	private String assemble(Object object, String tableName, String keyName, boolean increase) {
		//判断主键属性 拼接建表语句
		StringBuilder xx = new StringBuilder();
		xx.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append("(").append(keyName);
		//判断是否需要自增字段
		xx.append(increase ? " integer PRIMARY KEY autoincrement" : " text PRIMARY KEY");
		//组合后续属性
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (!field.getName().equals(keyName)) {
				xx.append(",").append(field.getName()).append(" text");
			}
		}
		xx.append(")");
		return xx.toString();
	}


	//===添加部分====================================================================

	/**
	 * 约定模式 添加数据
	 *
	 * @param o 实体
	 * @return 添加结果
	 */
	public int insert(Object o) throws TopException {
		if (!pattern) {
			throw new TopException(ERR);
		}
		return insert(getTableName(o.getClass()), o);
	}

	/**
	 * 普通 数据添加
	 * <p>
	 *
	 * @param tableName 数据添加表名
	 * @param object    包含数据的实体类
	 * @return 添加条数
	 */
	public int insert(String tableName, Object object) throws TopException {
		//执行添加语句
		return set(getInsertSqliteStr(tableName, object), "添加");
	}

	/**
	 * SqlLite添加,修改,删除,创建表等语句
	 *
	 * @param setSql sql
	 * @param msg    操作标题
	 * @return 操作条数
	 */
	public int set(String setSql, String msg) {
		int s;
		try {
			s = stmt.executeUpdate(setSql);
		} catch (SQLException e) {
			DealLog.log("set数据失败" + setSql, e);
			s = -1;
		}
		//不为空打印日志
		if (!msg.isEmpty()) {
			insertLog(msg, setSql, s == 1 ? "成功" : "失败");
		}
		return s;
	}

	//===更新部分====================================================================

	/**
	 * 约定模式 更新数据
	 *
	 * @param o 实体
	 * @return 更新结果
	 */
	public int update(Object o) throws TopException {
		if (!pattern) {
			throw new TopException(ERR);
		}
		return update(getTableName(o.getClass()), "id", o);
	}

	/**
	 * 普通模式 数据更新
	 * <p>
	 *
	 * @param tableName 表名
	 * @param keyName   主键名称
	 * @param object    数据实体
	 * @return 更新条数
	 */
	public int update(String tableName, String keyName, Object object) throws TopException {
		return set(getUpdateSqliteStr(tableName, object, keyName), "更新");
	}


	//===数据数量部分====================================================================

	public int getSum(Object o) throws TopException {
		if (!pattern) {
			throw new TopException(ERR);
		}
		List<Object> list = get(getTableName(o.getClass()), o);
		return list != null ? list.size() : 0;
	}


	/**
	 * 查询数量类
	 *
	 * @param getSql 查询数量类语句sql
	 * @return 数量
	 */
	public int getSum(String getSql) {
		return getSum(getSql, true);
	}

	/**
	 * 普通 sql 查询数量类
	 *
	 * @param getSql 查询数量类语句sql
	 * @param log    是否打印日志
	 * @return 数量
	 */
	public int getSum(String getSql, Boolean log) {
		int s;
		try (Statement statement = conn.createStatement();
		     ResultSet resultSet = statement.executeQuery(getSql)) {
			s = Integer.parseInt(resultSet.getObject(1).toString());
		} catch (SQLException e) {
			//出现异常 清空查询记录
			s = -1;
		}
		if (Boolean.TRUE.equals(log)) {
			insertLog("查询", getSql, "数量" + s);
		}
		return s;
	}
	//===分页查询====================================================================

	/**
	 * 分页查询数据
	 *
	 * @return 分页数据
	 */
	@SuppressWarnings("unchecked")
	public <T> PageData<T> getPage(Object page) throws TopException {
		if (!pattern) {
			throw new TopException(ERR);
		}
		PageData<T> pageData = (PageData<T>) page;

		//查询数据总条数
		pageData.setCount(getSum(pageData.getObject()));

		StringBuilder a = new StringBuilder();
		a.append(getSqliteStr(false, getTableName(pageData.getObject()), pageData.getObject(), pageData.getTimeList()));

		//获取携带的数据
		//每页数量
		int pageSize = pageData.getPageSize();
		//偏移量
		int getLimitBegin = pageData.getLimitBegin();
		//注意 LIMIT必须在后面
		//5.order by 本处只考虑单条件排序
		if (pageData.getOrderName() != null) {
			a.append(" ORDER BY ");
			a.append(pageData.getOrderName());
			a.append(Boolean.TRUE.equals(pageData.getOrderByDesc()) ? " DESC" : "");
		}

		//6.LIMIT [no of rows] OFFSET [row num]  查询 n条 从 m 开始
		if (pageSize >= 0) {
			a.append(" LIMIT ");
			a.append(pageSize);
			a.append(" OFFSET ");
			a.append(getLimitBegin);
		}

		//数据查询结果list
		pageData.setList((List<T>) get(pageData.getObject().getClass(), a.toString(), true));
		pageData.setSql(a.toString());
		return pageData;
	}
	//===数据查询部分====================================================================

	/**
	 * 约定模式 数据查询
	 *
	 * @param o   查询数据实体
	 * @param <T> 返回数据实体
	 * @return 返回实体list数据
	 */
	public <T> List<T> get(Object o) throws TopException {
		if (!pattern) {
			throw new TopException(ERR);
		}
		return get(getTableName(o.getClass()), o);
	}

	/**
	 * 约定模式 单数据查询
	 *
	 * @param o   查询数据实体
	 * @param <T> 返回数据实体
	 * @return 预期返回一条实体数据
	 */
	public <T> T getOne(Object o) throws TopException {
		if (!pattern) {
			throw new TopException(ERR);
		}
		List<T> list = get(getTableName(o.getClass()), o);
		if (list != null) {
			if (list.size() == 1) {
				return list.get(0);
			} else if (list.size() == 0) {
				throw new TopException("异常 查询数据结果为空");
			}
			throw new TopException("异常 查询出多条数据");
		}
		throw new TopException("异常 查询数据失败");
	}


	/**
	 * 普通模式 通过表的实体,查询数据
	 *
	 * @param tableName 表名称
	 * @param o         实体
	 * @param <T>       实体结果list
	 * @return 数据
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> get(String tableName, Object o) throws TopException {
		return (List<T>) get(o.getClass(), getSqliteStr(false, tableName, o, null), true);
	}

	/**
	 * 普通sql 查询模式
	 * <p>
	 * 注意:本方法未引入PreparedStatement 不能屏蔽sql注入
	 *
	 * @param t      实体类
	 * @param getSql 查询语句sql
	 * @return 查询结果list集合 查询结果以map保存 可强转处理为实体类
	 */
	public <T> List<T> get(Class<T> t, String getSql) {
		return get(t, getSql, true);
	}

	/**
	 * 底层查询模式  SqlLite查询类语句实现类 最底层与数据库交互
	 * 只接收完成的sql
	 * <p>
	 * 注意:本方法未引入PreparedStatement 不能屏蔽sql注入
	 *
	 * @param t      实体类
	 * @param getSql 查询语句sql
	 * @param log    是否打印日志
	 * @return 查询结果list集合 查询结果以map保存 可强转处理为实体类
	 */
	public <T> List<T> get(Class<T> t, String getSql, Boolean log) {
		List<T> list = new ArrayList<>();
		try (Statement statement = conn.createStatement(); ResultSet resultSet = statement.executeQuery(getSql)) {
			//获得结果集结构信息,元数据
			ResultSetMetaData md = resultSet.getMetaData();
			//获得列数
			int columnCount = md.getColumnCount();
			while (resultSet.next()) {
				// 查询结果map
				Map<String, Object> rowData = new HashMap<>(16);
				for (int i = 1; i <= columnCount; i++) {
					rowData.put(md.getColumnName(i), resultSet.getObject(i));
				}
				// 将map转换为class类型的数据
				list.add(BeanUtil.toBean(rowData, t));
			}
		} catch (SQLException e) {
			//出现异常 清空查询记录
			list = null;
		}
		if (Boolean.TRUE.equals(log)) {
			insertLog("查询", getSql, list == null ? "无" : list.toString());
		}
		return list;
	}

	//===删除部分====================================================================

	/**
	 * 约定模式 通过id删除数据
	 *
	 * @param o 删除数据必须包含id的实体
	 * @return 数量
	 */
	public int delete(Object o) throws TopException {
		return delete(null, o);
	}

	/**
	 * 基本模式 通过id删除数据
	 *
	 * @param o 删除数据必须包含id的实体
	 * @return 数量
	 */
	public int delete(String tableName, Object o) throws TopException {
		//删除模式需要用到主键,本处不能填入主键
		return set(getSqliteStr(true, tableName, o, null), "删除");
	}

	//===日志部分====================================================================

	/**
	 * 在日志表中插入一条数据
	 *
	 * @param key    标题
	 * @param value  操作具体
	 * @param result 结果  一般 0失败 1成功 其它为数据
	 */
	private void insertLog(String key, String value, String result) {
		String sql = "INSERT INTO FoundSqliteLog(date,key,value,result) VALUES('" + DealDate.getSqliteDate() + "','" + key + "','" + value.replace("'", "''") + "','" + result.replace("'", "''") + "')";
		set(sql, "");
	}

	/**
	 * 成功在日志表中插入一条数据
	 *
	 * @param key   标题
	 * @param value 操作具体
	 */
	private void insertLog(String key, String value) {
		insertLog(key, value, "1");
	}
}

