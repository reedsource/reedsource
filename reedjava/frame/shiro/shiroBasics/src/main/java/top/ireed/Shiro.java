/*
 * FileName: Shiro
 * Author:   reedsource
 */
package top.ireed;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import top.ireed.deal.DealLog;

/**
 * 功能简述:
 * 〈shiro单机简单实现〉
 *
 * @author reedsource
 * @version 1.0.0
 * date 2022/5/29 22:24
 * reedsource@189.cn
 *
 * //过期方案
 * //1.创建一个工厂对象，加载shiro.ini文件内容
 * Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
 * //2.由工厂对象负责创建一个SecuriyManager对象[shiro框架核心功能对象
 * SecurityManager manager = factory.getInstance();
 * //3.降低SecuriyManager对象使用难度，将SecuriyManager对象委托给SecuriyUtils
 * SecurityUtils.setSecurityManager(manager);
 */
public class Shiro {
	public static void main(String[] args) {
		//替代方案
		//1.由工厂对象负责创建一个DefaultSecurityManager对象[shiro框架核心功能对象]
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		//2.加载shiro.ini文件内容
		IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
		defaultSecurityManager.setRealm(iniRealm);
		//3.降低DefaultSecurityManager对象使用难度，将DefaultSecurityManager对象委托给SecuriyUtils
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		//4.SecuriyUtils提供给开发人员一个Subject对象
		Subject currentUser = SecurityUtils.getSubject();

		//5.检测 currentUser 是否已经登陆
		if (!currentUser.isAuthenticated()) {
			//6 如果没有登陆，进行登陆操作
			UsernamePasswordToken token = new UsernamePasswordToken("zs", "123");
			//7.进行登陆验证
			try {
				currentUser.login(token);
			} catch (UnknownAccountException e) {
				DealLog.log("登陆验证失败,输入的用户名是不存在的", e);
			} catch (IncorrectCredentialsException e) {
				DealLog.log("登陆验证失败,输入的密码是不存在", e);
			}

			/*
			 *  login方法判断登时使用信息是否真实的.
			 *  如果真实，则login方法不会有任何返回值。
			 *  此时调用isAuthenticated()时，其返回值是true
			 *
			 *  如果用户名或则密码不正确，则login方法将抛出对应异常
			 *  此时调用isAuthenticated()时，其返回值false
			 *
			 *  如果输出的密码是不正确的，此时login会向上抛出 IncorrectCredentialsException
			 *  如果输入的登陆名是不正确的，此时login会向上抛出 UnknownAccountException
			 */

			//8.获得当前用户的用户名
			DealLog.log("登陆时使用用户名 ", currentUser.getPrincipal());
			//9.判断用户是否拥有指定角色
			DealLog.log("是否拥有admin角色 ", currentUser.hasRole("admin"));
			DealLog.log("是否拥有user:*角色 ", currentUser.isPermitted("user:*"));
			//10.判断用户是否登陆
			DealLog.log("是否已经登陆 : ", currentUser.isAuthenticated());
		} else {
			DealLog.log("已经登陆了");
		}
		//清除当前用户登陆信息
		currentUser.logout();
	}
}
/*
1.user:query,user:insert,user:update,menu:show
【:】分隔符,分隔资源与操作 [资源:操作]
【,】分隔符,分隔多个权限
2.user:*,*:query
【*】通配符,可以表示所有【资源】与所有【操作】
【user:*】,即为 user 下所有【操作】
【*:query】,即为所有【资源】下 query 操作
3.细节
【user:*,可以表示 user:xx,还可以表示 user:xx:xxx】
【*:query,可以表示 xx:query,但是不能表示 xx:xx:query,
如果要表示可以这样写 *:*:query】
【user:update,user:delete】可以简写为
"user:update,delete",注意双引号必须加上
4.实例级权限标识 【资源:操作:实例】细化到某个具体资源实
例对象上
user:update:1 , user:delete:1
对用户 1 可以 update,对用户 1 可以删除
"user:update,delete:1" 与上面描述一致
*/