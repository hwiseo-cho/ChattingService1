package user;

public class UserDTO {

	private String userId;
	private String userPwd;
	private String name;
	private int age;
	private String gender;
	private String email;
	private String profile;

	public UserDTO() {
		super();
	}

	public UserDTO(String userId, String userPwd, String name, int age, String gender, String email, String profile) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.email = email;
		this.profile = profile;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userPwd=" + userPwd + ", name=" + name + ", age=" + age + ", gender="
				+ gender + ", email=" + email + ", profile=" + profile + "]";
	}
	
}
