public class TestLambda {
    //3.静态内部类
    static class Like2 implements ILike{
        public void lambda() {
            System.out.println("i like lambda 222");
        }
    }
    public static void main(String[] args) {
        //实现类
        ILike like = new Like();
        like.lambda();


//        TestLambda.Like2 like2 = new TestLambda.Like2();
//        like2.lambda();
        //3.静态内部类
        ILike like2 = new Like2();
        like2.lambda();

        //4.局部内部类
        class Like3 implements ILike{
            public void lambda() {
                System.out.println("i like lambda3333");
            }
        }

        ILike like3 = new Like3();
        like3.lambda();

        //5.匿名内部类, 没有类的名称， 必须借助接口或者父类,
        // 在这里我们看到使用匿名内部类我们必须要继承一个父类或者实现一个接口，
        // 当然也仅能只继承一个父类或者实现一个接口
        ILike like4 = new ILike(){
            public void lambda() {
                System.out.println("i like lambda4444");

            }
        };

        like4.lambda();
        //6. 用lambda简化
        ILike like5 = ()->{
            System.out.println("i like lambda5555");
        };

        like5.lambda();


    }
}

//1.定义一个函数式接口
interface ILike{
    void lambda();
}

//2.实现类
class Like implements ILike{
    public void lambda() {
        System.out.println("i like lambda");
    }
}
