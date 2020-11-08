package com.cliffside.controller;

/**
 * @author cliffside
 * @date 2020-11-06 18:42
 */
//public class HystrixTest extends HystrixCommand {
//
//
//    protected HystrixTest(HystrixCommandGroupKey group) {
//        super(group);
//    }
//
//    protected HystrixTest(HystrixCommandGroupKey group, HystrixThreadPoolKey threadPool) {
//        super(group, threadPool);
//    }
//
//    protected HystrixTest(HystrixCommandGroupKey group, int executionIsolationThreadTimeoutInMilliseconds) {
//        super(group, executionIsolationThreadTimeoutInMilliseconds);
//    }
//
//    protected HystrixTest(HystrixCommandGroupKey group, HystrixThreadPoolKey threadPool, int executionIsolationThreadTimeoutInMilliseconds) {
//        super(group, threadPool, executionIsolationThreadTimeoutInMilliseconds);
//    }
//
//    protected HystrixTest(Setter setter) {
//        super(setter);
//    }
//
//    public static void main(String[] args) {
//        //	HystrixTest hystrixTest = new HystrixTest(HystrixCommandGroupKey.Factory.asKey("ext"));
//        /**
//         * execute()：以同步阻塞方式执行run()。以demo为例，调用execute()后，
//         * hystrix先创建一个新线程运行run()，
//         * 	接着调用程序要在execute()调用处一直阻塞着，直到run()运行完成
//         */
//        //	System.out.println("result:" + hystrixTest.execute());
//
//        /**
//         * queue()：以异步非阻塞方式执行run()。以demo为例，
//         * 	一调用queue()就直接返回一个Future对象，
//         * 	同时hystrix创建一个新线程运行run()，
//         * 	调用程序通过Future.get()拿到run()的返回结果，
//         * 	而Future.get()是阻塞执行的
//         */
//
//        Future<String> futureResult = new HystrixTest(HystrixCommandGroupKey.Factory.asKey("ext")).queue();
//        String result ="";
//        try {
//            result =  futureResult.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        System.out.println("程序结果："+result);
//    }
//
//
//    @Override
//    //catch操作，备用逻辑
//    protected Object getFallback() {
//        return "getFallback";
//    }
//
//    @Override
//    protected Object run() throws Exception {
//        //就是try操作
//        System.out.println("执行逻辑");
//        int i =1/1;
////        return "ok";
////    }
//}
