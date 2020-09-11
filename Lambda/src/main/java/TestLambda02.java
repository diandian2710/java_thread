public class TestLambda02 {
    public static void main(String[] args) {
        ILove love = new Love();
        love.loveYou(1);


        love = new Love2();
        love.loveYou(1);

        //局部内部类
        class Love3 implements ILove{
            @Override
            public void loveYou(int a) {
                System.out.println("i love you3 "+a);
            }
        }

        love = new Love3();
        love.loveYou(1);

        //匿名内部类
        love = new ILove() {
            @Override
            public void loveYou(int a) {
                System.out.println("i love you4 " + a);
            }
        };
        love.loveYou(1);
       //lambda表达式
//        love = (int a) ->{
//            System.out.println("i love you5 "+ a);
//        };
       //lambda简化一：去掉参数类型
//        love = (a) ->{
//            System.out.println("i love you6 "+ a);
//        };

        //lambda简化二：简化括号
//        love = a ->{
//            System.out.println("i love you7 "+ a);
//        };

        //lambda简化三：简化大括号
        love = a -> System.out.println("i love you8 "+ a);

        //总结：
            //1.lambda表达式只能有一行代码的情况下才能简化成为一行，如果有多行，
            //那么就用代码块包裹
            //2.前提是接口为函数式接口 @FunctionalInterface
            //3.多个参数也可以去掉参数类型，要去掉就都去掉,必须加括号

        love.loveYou(1);
    }

    //静态内部类
    static class Love2 implements ILove{

        @Override
        public void loveYou(int a) {
            System.out.println("i love you2 "+a);
        }
    }


}


interface ILove{
    void loveYou(int a);

}
//实现类
class Love implements ILove{

    @Override
    public void loveYou(int a) {
        System.out.println("i love you "+a);
    }
}