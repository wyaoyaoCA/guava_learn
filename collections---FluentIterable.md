### collections---FluentIterable

#### 1 如何创建FluentIterable
```java
@Test
public void testCreateFluentIterable() {
    // 1 from 参数可以是一个数组，也可是一个java.lang.Iterable 可迭代类，比如List
    FluentIterable<String> from = FluentIterable.from(Arrays.asList("java", "python", "php", "C#", "javaScript"));

    // 2 of方法
    FluentIterable.of();
    // 2.1 可变参数
    FluentIterable<String> of = FluentIterable.of("java", "python", "php", "C#", "javaScript");
}

```

#### 2 方法
- filter
```java
private FluentIterable<String> build() {
        return FluentIterable.from(Arrays.asList("java", "python", "php", "C#", "javaScript"));
    }
@Test
public void testFilter() {
    FluentIterable<String> fit = build();
    assertThat(fit.size(), equalTo(5));

    // 过滤出不为null且长度大于4的
    FluentIterable<String> result = fit.filter(item -> item != null && item.length() > 4);
    assertThat(result.size(), equalTo(2));
}

```
- append
```java
/**
 * append: 追加元素，注意返回一个新的FluentIterable
 * 可以接收一个Iterable，也可以是可变参数
 */
@Test
public void testAppend() {
    FluentIterable<String> fit = build();
    assertThat(fit.size(), equalTo(5));

    List<String> strings = Arrays.asList("html", "Android", "css");
    FluentIterable<String> append = fit.append(strings);
    assertThat(append.size(), equalTo(8));
    assertThat(append.contains("Android"), equalTo(true));
}

```
- match: 匹配
```java
@Test
    public void testMatch() {
        FluentIterable<String> fit = build();
        /**
         * allMatch: 全部满足
         */
        boolean b = fit.allMatch(item -> item != null && item.length() > 4);
        assertThat(b, equalTo(false));

        /**
         * anyMatch : 任意一个满足
         */
        boolean r = fit.anyMatch(item -> item != null && item.length() > 4);
        assertThat(r, equalTo(true));

        /**
         * firstMatch: 找到第一个满足条件的元素
         */
        Optional<String> optional = fit.firstMatch(item -> item != null && item.length() > 4);
        assertThat(optional.isPresent(),equalTo(true));
        assertThat(optional.get(),equalTo("python"));
    }

```
- first：拿出第一个元素
- last：最后一个元素
```java
@Test
    public void testFirstAndLast(){
        /**
         * first:
         */
        Optional<String> first = build().first();
        assertThat(first.isPresent(),equalTo(true));
        assertThat(first.get(),equalTo("java"));

        /**
         * last
         */
        Optional<String> last = build().last();
        assertThat(last.isPresent(),equalTo(true));
        assertThat(last.get(),equalTo("javaScript"));
    }

```
- limit: 截取前几个
```java
@Test
public void testLimit(){
    FluentIterable<String> fit = build();
    FluentIterable<String> limit = fit.limit(3);
    System.out.println(limit);
    assertThat(limit.size(),equalTo(3));
    assertThat(limit.get(0),equalTo("java"));
    assertThat(limit.get(1),equalTo("python"));
    assertThat(limit.get(2),equalTo("php"));
}
```

- CopyInto：复制指定集合到现有集合，返回一个新的集合，原有集合不变
```java
@Test
public void testCopyInto(){
    FluentIterable<String> fit = build();

    ArrayList<String> strings = Lists.newArrayList("html", "Android", "css");

    List<String> result = fit.copyInto(strings);
    assertThat(result.size(),equalTo(8));
    assertThat(fit.size(),equalTo(5));
    assertThat(result.contains("css"),equalTo(true));
}
```
- Cycle
```java
@Test
    public void testCycle(){
        FluentIterable<String> fit = build();
        FluentIterable<String> cycle = fit.cycle();
        // 会一直循环打印
        // cycle.forEach(System.out::println);
    }
```
- Transform
```java
/**
 * java8 stream的map
 */
@Test
public void testTransform(){
    FluentIterable<String> fit = build();
    // 将String -> int
    fit.transform(item -> item.length()).forEach(System.out::println);
}
```
- join：将元素使用指定的符号拼成一个字符串
```java
@Test
    public void testJoin(){
        FluentIterable<String> fit = build();
        String join = fit.join(Joiner.on(','));
        System.out.println(join);
        assertThat(join,equalTo("java,python,php,C#,javaScript"));

    }
```
- TransformAndConca : 遍历元素，对每个元素进行处理，且处理的结果是个FluentIterable
最后在合并成一个FluentIterable
```java
/****
     * 场景：假设会员有两种类型分别为 1 和 2
     * 根据会员类型查询会员，最后后将查询结果合并
     */
    @Test
    public void testTransformAndConcat(){
        List<Integer> types = Lists.newArrayList(1, 2);
        FluentIterable<Member> members = FluentIterable.from(types).transformAndConcat(type -> searchMemberByType(type));
        members.forEach(System.out::println);
    }
    // 模拟查询会员
    private List<Member> searchMemberByType(Integer type){
        if(type ==1 ){
            // 假设会员类型为1 有两个会员 张三李四
            return Lists.newArrayList(new Member(type,"张三"),new Member(type,"李四"));
        }else{
            // 假设会员类型为2 有三个会员 王五 陈六 钱八
            return Lists.newArrayList(new Member(type,"王五"),new Member(type,"陈六"),new Member(type,"钱八"));
        }
    }
    class Member{
        // 会员类型
        Integer type;
        // 会员名字
        String name;

        public Member(Integer type, String name) {
            this.type = type;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Member{" +
                    "type=" + type +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
```