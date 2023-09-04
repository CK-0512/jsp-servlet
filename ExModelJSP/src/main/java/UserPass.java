import model.util.UserSHA256;

public class UserPass {
	public static void main(String[] args) {
		String pass = "1234";
		
		String endPass = UserSHA256.getSHA256(pass);
		System.out.println(endPass);
	}
}
