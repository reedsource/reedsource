package sql;
// 注意:在JDBC编程时要导入java.sql.*; 不要导入其他的包!

import top.ireed.deal.DealLog;

import java.sql.*;

/*
使用JDBC规范连接数据库的步骤

0.准备数据库驱动
(1)在项目中创建一个普通的文件夹lib(lib表示外部类库的意思)
(2)把mysql-connector-java-5.1.23-bin.jar拷贝到lib目录中
(3)把mysql-connector-java-5.1.23-bin.jar添加到Eclipse的构建路径中,让Eclipse可以识别jar文件中的字节码
   右键点击mysql-connector-java-5.1.23-bin.jar -> build path -> add to build path

------------------------------------------------------------------------------------------
JDBC编程的标准六部曲
1.注册数据库驱动,让JVM可以识别MySql的数据库驱动类
2.获取和数据库的连接对象
3.通过连接对象获取SQL语句对象
4.通过语句对象执行具体的SQL语句,例如执行DQL,数据库返回查询结果集
5.遍历结果集,取出每条数据中的字段信息
6.数据库的相关对象资源是有限的,用完之后必须要及时关闭

由于mysql数据库必须依赖于软件,安装服务器环境与安装数据库环境是一起的,所以一般不考虑数据库的表是否创建的问题
 */
public class jdbc_mysql {
	// 调用Statement上面的executeQuery()来执行DQL语句
	private static void test1() {
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;
		String sql;


		// 2.获取和数据库的连接
		// (1)准备数据库服务器的相关信息

		//用户
		String user = "root";
		//密码
		String password = "root";
		// url:统一资源定位器,定位到网络中的数据库服务器
		// jdbc:表示jdbc网络协议; jdbc:mysql是jdbc网络协议下面的mysql子协议

		//地址及表

		//需要连接判断的库名
		String ku = "yinxiang";
		//需要操作的表名
		String surface = "USER";
		//需要操作的数据库的路径
		String url = "jdbc:mysql://localhost:3306/" + ku;

		// (2)Java中把和数据库的连接封装成Connection接口
		// Connection是sun公司声明的接口.DriverManager.getConnection()返回的是什么东西?
		// 返回的是是一个具体类的对象,这个具体类肯定实现了Connection接口,这个具体类是MySql公司实现的
		//连接数据库


		try {
			// 1.注册数据库驱动,让JVM可以识别MySql的数据库驱动类
			// Driver是Sun公司制定的接口,com.mysql.jdbc.Driver是MySql公司对Driver接口的实现类
			Driver driver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(driver);


			//以下为库存在校验-----------------------------------------------------------------------------
			try {
				conn = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				DealLog.log("数据库 " + ku + " 不存在,系统正在自动创建");

				//库如果不存在,将连接数据库必然带的mysql数据库
				url = "jdbc:mysql://localhost:3306/mysql";
				conn = DriverManager.getConnection(url, user, password);
				stat = conn.createStatement();

				// 3.通过连接对象获取SQL语句对象
				// Java中把SQL语句封装成Statement(语句的意思)接口
				// conn.createStatement()返回的是什么?
				// conn.createStatement()返回的是一个具体类的对象,这个具体类实现了Statement接口,这个具体类是MySql公司实现的

				//连接成功后,执行建库语句
				//执行后,返回创建的表的数量,如果数量为1,表示库创建成功
				sql = "CREATE DATABASE " + ku;
				int m = stat.executeUpdate(sql);
				DealLog.log("成功建立数据库 " + ku + " 成功标识:" + m);

				//关闭已经连接的mysql库
				conn.close();
				//以上为库存在校验-----------------------------------------------------------------------------
				//以上操作后,将判断需要操作的数据库是否存在,如果不存在将创建
			} finally {
				//以下为表存在校验-----------------------------------------------------------------------------

				//连接数据库
				url = "jdbc:mysql://localhost:3306/" + ku;

				conn = DriverManager.getConnection(url, user, password);
				stat = conn.createStatement();

				// 4.通过语句对象执行具体的SQL语句,例如执行DQL,数据库返回查询结果集
				// (1)准备具体的DQL语句.为了代码的正确性,首先在数据库中编写SQL语句,没有问题在拷贝到Java中

				try {
					//尝试创建数据表,如果无异常表示数据库表不存在,正常创建,如果出现已经存在的异常,表示表存在

					DealLog.log("校验数据表 " + surface + " 状态");
					//用户表 自增长ID 时间 姓名 性别 年龄
					sql = "CREATE TABLE " + surface + " ( ID INT (7) AUTO_INCREMENT, DATA DATE, NAME VARCHAR (10), SEX CHAR (1), AGE VARCHAR (10), PRIMARY KEY (ID));";
					int m = stat.executeUpdate(sql);
					DealLog.log(m == 0 ? surface + " 建表完成" : surface + "建表失败");
					//注意:建表语句影响的行数是0,如果返回的是0表示建表成功,异常信息表示失败
				} catch (SQLException e1) {
					DealLog.log(e1);
				} finally {
					//以上操作后,数据库初始化判断完成,库及表可以使用,可以作为模块使用

					//以下为批量添加数据
					String data;   //="'2018-10-10'";
					String name;
					String sex;
					String age;

					for (int x = 1; x <= 10; x++) {
						data = "'2010-01-" + x + "'";
						name = "\"张" + x + "\"";
						sex = x % 2 == 1 ? "'男'" : "'女'";
						age = String.valueOf((20 + x));
						sql = "INSERT INTO " + surface + " (DATA,NAME,SEX,AGE) VALUES (" + data + "," + name + "," + sex + "," + age + ")"; // sql语句末尾的分号可有可无
						DealLog.log(sql);
						int m = stat.executeUpdate(sql);
						DealLog.log("添加新记录 第" + x + "行", m);
					}
				}

				//
				// (2)SQL语句有不同的种类,所以Statement上面有不同的方法来执行不同的SQL语句
				// 我们调用Statement上面的executeQuery()来执行DQL语句,返回查询结果集
				// Java中把查询结果集封装成ResultSet接口
				// res = stat.executeQuery(sql);

				DealLog.log("开始查询全表");

				sql = "SELECT * FROM " + surface;
				res = stat.executeQuery(sql);

				// 5.遍历结果集,取出每条数据中的字段信息
				while (res.next()) {
					// res.next()有两个作用:(1)判断结果集中是否有数据;(2)迭代器移动到这一条数据
					// 取出这条数据中的每个字段
					int no = res.getInt(1); // 按照字段在结果集中的位置来取得字段信息,位置从1开始
					Date data = res.getDate("data");//获取时间字段
					String name = res.getString("name");// 按照字段名称来取得字段信息,字段名称不区分大小写
					String sex = res.getString("sex");
					String age = res.getString("age");

					DealLog.log(no + "-" + name + "," + data + "," + sex + "," + age);
				}
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 6.数据库的相关对象资源是有限的,用完之后必须要及时关闭
			// 注意:在关闭数据库相关对象的时候,必须要反向关闭
			// (1)在方法中处理异常的时候,可以合着处理,也就是一个try{}跟多个catch(),也可以分着处理,也就是分别使用try{}catch(){}
			// 何时应该合着处理?何时应该分着处理呢?
			// 当多个操作之间有联系的时候应该合着处理;当多个操作之间没有联系的时候,应该分着处理!
			// (2)如果获取结果集失败,res是一个null,直接调用null.close()会导致异常,通过一个判断语句避免异常
			try {
				if (res != null) {
					res.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (stat != null) {
					stat.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	// 调用Statement上面的executeUpdate()执行DML/DDL
	public static void test2() {
		Connection conn = null;
		Statement stat = null;

		try {
			Driver driver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(driver);

			String user = "root";
			String password = "root";
			String url = "jdbc:mysql://localhost:3306/bjpowernode";

			conn = DriverManager.getConnection(url, user, password);
			stat = conn.createStatement();

			/////////////////////////////////////////////////////////////////////////////////
			// 使用executeUpdate()执行DML语句
			// String sql = "INSERT INTO EMP VALUES
			// (9000,'张0','姓名经理',7902,STR_TO_DATE('1982-5-18','%Y-%m-%d'),1600,200,30)";
			//
			// // 我们执行DML的时候,数据库返回的是收到DML语句影响的数据的条数,称为更新计数器
			// int i = stat.executeUpdate(sql);

			/////////////////////////////////////////////////////////////////////////////////
			// 使用executeUpdate()执行DDL语句
			String sql = "CREATE TABLE USERS (ID  INT(4) PRIMARY KEY ,NAME VARCHAR(10) ,PASSWORD VARCHAR(10))";

			// 执行DDL语句的时候,更新计数器是0
			int i = stat.executeUpdate(sql);
			DealLog.log("更新计数器i=" + i);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stat != null) {
					stat.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 调用Statement上面的execute()执行DQL/DML/DDL
	public static void test3() {
		Connection conn = null;
		Statement stat = null;
		ResultSet res = null;

		try {
			Driver driver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(driver);

			String user = "root";
			String password = "root";
			String url = "jdbc:mysql://localhost:3306/bjpowernode";

			conn = DriverManager.getConnection(url, user, password);
			stat = conn.createStatement();

			//////////////////////////////////////////////////////////////////////////////////
			// 调用Statement上面的execute()执行DQL
			// String sql = "SELECT * FROM EMP WHERE DEPTNO = 20";
			// // 如果结果为 ResultSet对象，则stat.execute()返回 true
			// if (stat.execute(sql)) {
			// // 必须使用方法 getResultSet()取得结果集对象
			// res = stat.getResultSet();
			//
			// while (res.next()) {
			// // res.next()有两个作用:(1)判断结果集中是否有数据;(2)迭代器移动到这一条数据
			// // 取出这条数据中的每个字段
			// int empno1 = res.getInt(1); // 按照字段在结果集中的位置来取得字段信息,位置从1开始
			// int empno2 = res.getInt("EmPNo"); // 按照字段名称来取得字段信息,字段名称不区分大小写
			// String ename = res.getString("ename");
			// String job = res.getString("job");
			// int mgr = res.getInt("mgr");
			// java.sql.Date hiredate = res.getDate("hiredate");
			// double sal = res.getDouble("sal");
			// float comm = res.getFloat("comm");
			// int deptno = res.getInt("deptno");
			//
			// DealLog.log(empno1 + "-" + empno2 + "," + ename + "," +
			// job + "," + mgr + "," + hiredate
			// + "," + sal + "," + comm + "," + deptno);
			// }
			// }

			//////////////////////////////////////////////////////////////////////////////////
			// 调用Statement上面的execute()执行DML/DDL
			String sql = "INSERT INTO EMP VALUES(9001,'张1','姓名经理',7902,STR_TO_DATE('1982-5-18','%Y-%m-%d'),1600,200,30)";

			// 如果其为更新计数或者不存在任何结果，则stat.execute()返回 false
			if (!stat.execute(sql)) {
				// 通过getUpdateCount 来获取更新计数器
				int i = stat.getUpdateCount();
				DealLog.log("更新计数器是:" + i);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				if (res != null) {
					res.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (stat != null) {
					stat.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		test1();
		// test2();
		//test3();
	}
}
