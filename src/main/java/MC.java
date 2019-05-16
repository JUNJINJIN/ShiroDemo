import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
public class MC {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		// 2,ʵ����securityManager
		SecurityManager securityManager = factory.getInstance();
		// 3��ʹ���Ե���ģʽ��Ч
		SecurityUtils.setSecurityManager(securityManager);

		// ��ȡ��ǰ�������û�ƾ֤
		Subject sub = SecurityUtils.getSubject();
		Session session = sub.getSession();
		session.setAttribute("key", "value");
		if (!sub.isAuthenticated()) {// ����û���δ��֤ƾ֤
			UsernamePasswordToken upt = new UsernamePasswordToken("jane", "123");// ���е�¼
			upt.setRememberMe(true);// ��ס�����������Ҫ�Լ�ʵ�֣�û�����õ�
			try {
				sub.login(upt);// ִ�е�¼����
				System.out.println("��ǰ��½���û�Ϊ��" + sub.getPrincipal());
				
				//�ж�user�����Ƿ���role���еĽ�ɫ
				if (sub.hasRole("��������Ա")) {// ����û��Ƿ�߱�ĳ�ֽ�ɫ
					System.out.println("���û��߱���������Ա��ɫ");
				} else {
					System.out.println("���û����߱���������Ա��ɫ");
				}
				
				//�ж�user�����Ƿ���permission���е�Ȩ��
				if(sub.isPermitted("����")){
					System.out.println("���û�������Ȩ��");
				}else{
					System.out.println("���û�û������Ȩ��");
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�û����������");
			}
			
		}
		
	}
}
