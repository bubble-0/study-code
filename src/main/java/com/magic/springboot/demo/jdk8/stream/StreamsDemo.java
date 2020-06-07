package com.magic.springboot.demo.jdk8.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class StreamsDemo {
    private enum Status {
        OPEN, CLOSED
    }

    ;

    private static final class Task {
        private final Status status;
        private final Integer points;

        Task(final Status status, final Integer points) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return points;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return String.format("[%s, %d]", status, points);
        }
    }

    public static void main(String[] args) {
        /**
         * 新增的Stream API（java.util.stream）将生成环境的函数式编程引入了Java库中。
         * 这是目前为止最大的一次对Java库的完善，以便开发者能够写出更加有效、
         * 更加简洁和紧凑的代码。
         *
         * Stream（流）是一个来自数据源的元素队列并支持聚合操作
         * 元素是特定类型的对象，形成一个队列。 Java中的Stream并不会存储元素，而是按需计算。
         * 数据源 流的来源。 可以是集合，数组，I/O channel， 产生器generator 等。
         * 聚合操作 类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等。
         *
         * 和以前的Collection操作不同， Stream操作还有两个基础的特征：
         * Pipelining: 中间操作都会返回流对象本身。 这样多个操作可以串联成一个管道， 如同流式风格（fluent style）。 这样做可以对操作进行优化， 比如延迟执行(laziness)和短路( short-circuiting)。
         * 内部迭代： 以前对集合遍历都是通过Iterator或者For-Each的方式, 显式的在集合外部进行迭代， 这叫做外部迭代。 Stream提供了内部迭代的方式， 通过访问者模式(Visitor)实现
         *
         * 新增加的双冒号用法::
         * 就是把方法当做参数传到stream内部，使stream的每个元素都传入到该方法里面执行一下，双冒号运算就是Java中的[方法引用],[方法引用]的格式是：
         * 类名：：方法名
         * 注意此处没有（）.
         * 例如:
         *
         * 表达式：
         * person -> person.getAge();
         *
         * 使用双冒号：
         * Person：：getAge
         *
         * 表达式：
         * new HashMap<>()
         *
         * 使用双冒号：
         * HsahMap :: new
         */

        final Collection<Task> tasks = Arrays.asList(
                new Task(Status.OPEN, 10),
                new Task(Status.OPEN, 10),
                new Task(Status.CLOSED, 10)
        );

        long totalPointsOfOpenTasks = tasks.stream().filter(task ->
                task.getStatus() == Status.OPEN
        ).mapToInt(Task::getPoints).sum();

        System.out.println("打开状态的point总和为: " + totalPointsOfOpenTasks);

        /**
         * 需要记住一些steams（点此更多细节）的知识点。
         *
         * Steam之上的操作可分为中间操作和晚期操作:
         *
         * 中间操作会返回一个新的steam——执行一个中间操作（例如filter）并不会执行实际的过滤操作，
         * 而是创建一个新的steam，并将原steam中符合条件的元素放入新创建的steam。
         *
         * 晚期操作（例如forEach或者sum），会遍历steam并得出结果或者附带结果；在执行晚期操作之后，
         * steam处理线已经处理完毕，就不能使用了。在几乎所有情况下，晚期操作都是立刻对steam进行遍历。
         */

        /**
         * team的另一个价值是创造性地支持并行处理（parallel processing）。
         * 对于上述的tasks集合，我们可以用下面的代码计算所有task的points之和：
         */

        int sumTaskPoint1 = tasks.stream().mapToInt(Task::getPoints).sum();
        System.out.println("所有状态的point总和为: " + sumTaskPoint1);

        int sumTaskPoint2 = tasks.stream()
                .parallel()
                .map(task -> task.getPoints())
                .reduce(0, Integer::sum);
        System.out.println("parallel 所有状态的point总和为: " + sumTaskPoint2);

        /**
         * 对于一个集合，经常需要根据某些条件对其中的元素分组。利用steam提供的API可以很快完成这类任务
         */
        Map<Status, List<Task>> map = tasks.stream().collect(Collectors.groupingBy(Task::getStatus));
        System.out.println("根据状态排序map: " + map);

        /**
         * 最后一个关于tasks集合的例子问题是：如何计算集合中每个任务的点数在集合中所占的比重
         */
        final Collection<String> result = tasks.stream()
                .mapToInt(Task::getPoints)
                .asLongStream()
                .mapToDouble(points -> points / sumTaskPoint1)
                .boxed()                                         // Stream< Double >
                .mapToLong(weigth -> (long) (weigth * 100)) // LongStream
                .mapToObj(percentage -> percentage + "%")      // Stream< String>
                .collect(Collectors.toList());

        System.out.println(result);



    }
}
