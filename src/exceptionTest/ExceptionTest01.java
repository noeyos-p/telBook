package exceptionTest;

public class ExceptionTest01 {
    public static void main(String[] args) {
        int a =10;
        int b = 2;
        try { // 시도하다
            int c = a / b;
            System.out.println(c);
        } catch (ArithmeticException e) {
            System.out.println("0으로 나누면 미워요");
        } catch (Exception e) {
            System.out.println("예외가 발생하였습니다.");
        } finally {
            System.out.println("여기는 무조건 실행돼요");
        }
        // 오류가 나도 Process finished with exit code 0 으로 끝남.
        // Arithmetic Exception e -> 산술연산만 잡는애
        // Exception e -> 모든 오류를 잡는 애
        // finally 무조건 실행되는 것
    }
}
