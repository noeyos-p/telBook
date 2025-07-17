package exceptionTest;

public class ExceptionThrowTest {
    public static void divide(int a, int b) throws ArithmeticException{
        if (b == 0) {
        throw new ArithmeticException("0으로 나누면 안돼요!");
        }
        int c = a/b;
        System.out.println(c);
    }
    public static void main(String[] args) {
        int a = 10;
        int b = 0;
        try {
            divide(a, b);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
            // 오류가 나면 이쪽으로 이동
        }

        // 이런상태에서는 오류가 있는지 없는지 파악할 수 있는 방법이 없음
        // 오류가 난 것을 봤을 때
        /*Exception in thread "main" java.lang.ArithmeticException: / by zero
        at exceptionTest.ExceptionThrowTest.divide(ExceptionThrowTest.java:5)
        at exceptionTest.ExceptionThrowTest.main(ExceptionThrowTest.java:11)*/
        // 두번째 줄이 오류가 생겨서 밑에도 오류가 생겼다고 말해줌
        // 그래서 두번쨰 줄을 잘 봐야함
        // try catch는 내가 해결할게
        // 이 식은 니가 해결해 -> throw 라고 함
    }
}
