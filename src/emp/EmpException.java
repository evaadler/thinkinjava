package emp;

public class EmpException extends RuntimeException {
	//TODO 根据父类创建构造方法
	
	private static final long serialVersionUID = 1153342132242044112L;

	public EmpException() {
		super();
	}

	public EmpException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmpException(String message) {
		super(message);
	}

	public EmpException(Throwable cause) {
		super(cause);
	}
	
	
	
}
