package exception;

public class MyException extends  Exception{
    public static final Long serialVersionUID = 1L;
    // Long 쓸때 숫자뒤에 L을 항상 넣어줘야함
    public MyException(){}
    // default 생성자, 기본 생성자

    public MyException(String message) {
        super(message);

    }


}
