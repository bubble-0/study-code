package com.magic.springboot.demo.jdk8.stream;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UseStreamDemo {

    public static void main(String[] args) {

        Integer[] arr = {61, 24, 33, 41, 35, 26, 17, 328, 19, 10, 100, 102, 103};

        /**
         * 过滤掉大于100的元素,转化成String
         */

        //排序
        Arrays.stream(arr).filter(i -> i < 100 ? true : false).sorted().forEach(i -> System.out.print(i + ","));

        System.out.println();

        //limit
        Stream.of(61, 24, 33, 41, 35, 26, 17, 328, 19, 10, 100, 102, 103).filter(i -> i > 100 ? false : true).sorted().limit(5).forEach(i -> System.out.print(i + ","));
        System.out.println();

        //skip
        Stream.of(61, 24, 33, 41, 35, 26, 17, 328, 19, 10, 100, 102, 103).filter(i -> i < 100 ? true : false).sorted().skip(5).forEach(i -> {
            System.out.print(i + ",");
        });

        System.out.println();

        //peek
        Stream.of(61, 24, 33, 41, 35, 26, 17, 328, 19, 10, 100, 102, 103).sorted().peek(i -> {
            if (i > 100) {
                System.out.print("大于100的有" + i);
            }
        }).forEach(i -> {
            System.out.print(i + ",");
        });

        System.out.println();

        //flatMap
        //flatMap是将T类型元素计算并转化成Stream<R>类型元素，并将多个Stream<R>合并成一个Stream。
        //也就是说flatMap可以将原Stream中的元素进行全新的展开计算，最终归纳成一个新的流
        Stream.of(1, 2, 3, 4, 5).flatMap(e -> Stream.of(e, e)).forEach(i -> {
            System.out.print(i + ",");
        });

        System.out.println();

        /**
         * 最终方法
         */

        Comparator<Integer> co = (e1, e2) -> e1 - e2;

        //min
        List<Integer> s = Arrays.asList(arr);
        System.out.println("最小元素 " + s.stream().sorted().min(co).get());

        //max
        List<Integer> s2 = Arrays.asList(arr);
        System.out.println("最大元素 " + s2.stream().sorted().max(co).get());

        //count
        List<Integer> s3 = Arrays.asList(arr);
        System.out.println("count " + s3.stream().filter(i -> i < 100 ? true : false).map(i -> String.valueOf(i)).count());

        //Match
        System.out.println("是否全部小于10? " + Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).allMatch(i -> i < 10 ? true : false));

        System.out.println("是否有一个大于10? " + Stream.of(1, 2, 3, 4, 5, 10).anyMatch(i -> i > 10));

        System.out.println("是否全部都不大于10? " + Stream.of(1, 2, 3, 4, 5, 6).noneMatch(i -> i > 10));

        /**
         * 将map的age转换成一个集合
         */

        /**
         * 简单使用
         * <R> R collect(Supplier<R> supplier, BiConsumer<R,? super T> accumulator, BiConsumer<R,R> combiner)
         *
         * Supplier<R> supplie：该函数接口的参数类型"R"与collect返回的类型是一致的，
         * 方法"T get()"的目的就是返回一个新对象，如果我们想将Stream转化成List，
         * get方法内应该返回一个List实例（如果想将Stream转化成String，get方法返回一个String实例）。一般来说，该方法是构造方法引用的最佳实践，如"ArrayList::new"等
         *
         * BiConsumer<R,? super T> accumulator：该函数接口参数类型是二元组泛型，
         * 其中"R"表示返回类型，"T"表示Stream的元素类型。
         * 接口方法"accept(R r, T t)"的目的是建立目标容器与Stream元素之间的收集关系。
         * 例如我们要将Stream转化成List，则accept方法内部应该是将Stream中的元素加入到List中。
         *
         *  BiConsumer<R,R> combiner：该函数接口的目的是将多个容器内的元素叠加到一起。
         *
         *  在单线程的情况下，参数supplier和accumulator就可以实现collect到目标容器的收集转化操作。
         *  最后一个参数combiner起到了多个容器累加的作用, 一般情况下我们创建或者获取的Stream是一个顺序流，该参数是没有效果的，
         *  我们是可以忽略该参数的。如果Stream是一个通过集合类的parallelStream方法生成的并行流，则该参数是有效的。
         */
        //简单使用
        Integer[] arrs = {12, 123, 12, 434, 65, 65, 22, 31, 21, 3, 12};

        List<Integer> lists = Stream.of(arrs).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("lists" + lists);

        /**
         * 把Stream<Integer> 转换为String
         * 需要注意String类型的值不能修改,值修改意味着重新引用,所以不要使用不能修改的类型作为返回容器
         */
        String str = Stream.of(arrs).collect(StringBuffer::new,
                (e1, e2) -> e1.append(e2).append(","),
                (e3, e4) -> e3.append(e4)).toString();
        System.out.println("转换为String: " + str);

        /**
         * collect 实现filter
         */
        //判断是否为偶数
        Predicate<Integer> pre = (i) -> i % 2 == 0 ? true : false;
        List<Integer> result3 = Stream.of(arrs).collect(ArrayList::new, (e1, e2) -> {
            if (pre.test(e2)) {
                e1.add(e2);
            }
        }, (ls1, ls2) -> ls1.addAll(ls2));
        System.out.println("collect 实现filter" + result3);

        /**
         * collect 实现map
         */
        List<Integer> result4 = Stream.of(arrs).collect(ArrayList::new, (e1, e2) ->
                        e1.add(e2 * e2)
                , (ls1, ls2) -> ls1.addAll(ls2));
        System.out.println("平方 " + result4);

        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("age", "11");
        map1.put("sex", "0");

        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("age", "12");
        map2.put("sex", "1");

        Map<String, String> map3 = new HashMap<String, String>();
        map3.put("age", "13");
        map3.put("sex", "1");

        Map<String, String> map4 = new HashMap<String, String>();
        map4.put("age", "14");
        map4.put("sex", "0");

        mapList.add(map1);
        mapList.add(map2);
        mapList.add(map3);
        mapList.add(map4);

        mapList.stream().collect(ArrayList::new,
                (ls, e) -> ls.add(" new map " + e.get("age")),
                ArrayList::addAll).forEach(System.out::print);


        System.out.println();

        /**
         * Stream默认为顺序流（单线程梳理流），collect方法的第三个参数combiner并没有实际的意义，
         * 但在并行流的环境下，该参数必须实现容器累加的算法，
         * 否则最终收集的元素的容器会出现丢失元素的情况，如下面示例所示。
         * 如果写成 (ls1, ls2)->{} 则元素显示不全 (ls1, ls2)->{ls1.addAll(ls2)}
         */
        List<Integer> demoList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> newdemoList = demoList.parallelStream().collect(ArrayList::new, (ls, e) -> ls.add(e * e), (ls1, ls2) -> {
        });
        newdemoList.forEach(System.out::println);
        System.out.println(newdemoList.size());


        /**
         * 归纳操作reduce
         * reduce方法也称归纳方法，它可以将Stream中的元素归纳成一个对象，
         * 在归纳的过程中也可以对Stream中的元素进行操作，从方法描述上来看，
         * reduce是可以代替collect方法。Stream中的reduce方法共有三个重载方法
         * 1.reduce(BinaryOperator<T> accumulator)
         *
         * 2.reduce(T identity, BinaryOperator<T> accumulator)
         *
         * 3.reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)
         */
        Optional<Integer> optional = demoList.stream().reduce((e1, e2) -> {
            System.out.println(" e1= " + e1);
            System.out.println(" e2= " + e2);
            return e1 + e2;
        });

        System.out.println(optional.get());


        Integer sum = demoList.stream().reduce(-10, (e1, e2) -> e1 + e2);
        System.out.println(sum);

        ArrayList<Integer> newlist = demoList.stream().reduce(new ArrayList<Integer>(), (ls, e) -> {
            ls.add(e*e);
            return ls;
        }, (ls1, ls2) -> {
            ls1.addAll(ls2);
            return ls1;
        });

        newlist.forEach(System.out::println);

        /**
         * Collectors 实现了接口 Collector<T,A,R>
         * T: 需要进行reduce操作的元素类型
         * A:reduce操作的动态集合类型
         * R:reduce操作的结果类型
         */

        //将map中的年龄转换为list
        List<String> ageList = mapList.stream().map(e->e.get("age")).collect(Collectors.toList());
        ageList.forEach(System.out::println);

        //将集合中的性别转换为set
        Set<String> sexSet = mapList.stream().map(e->e.get("sex")).collect(Collectors.toCollection(TreeSet::new));
        sexSet.forEach(System.out::println);

        //将年龄拼接成一个字符串
        String joinStr = mapList.stream().map(e->e.get("age")).collect(Collectors.joining("+"));
        String joinStr2 = mapList.stream().map(e->e.get("age")).collect(Collectors.joining(""));
        System.out.println(joinStr);
        System.out.println(joinStr2);

        //计算年龄总和
        int total = mapList.stream().collect(Collectors.summingInt(e->Integer.parseInt(e.get("age"))));
        System.out.println(total);

        //根据性别分组
        Map<String, List<Map<String, String>>> sexGroup = mapList.stream().collect(Collectors.groupingBy(e-> e.get("sex")));
        System.out.println(sexGroup);

        //计算不同性别的年龄
        Map<String, Integer> sexGroupSumAge = mapList.stream().collect(Collectors.groupingBy(e->e.get("sex"), Collectors.summingInt(e-> Integer.parseInt(e.get("age")))));
        System.out.println(sexGroupSumAge);

        
    }
}
