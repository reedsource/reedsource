package main // 声明 main 包，表明当前是一个可执行程序

import (
	"fmt"
	"math"
) // 声明 main 包，表明当前是一个可执行程序

/*
1. init函数是用于程序执行前做包的初始化的函数，比如初始化包里的变量等
2. 每个包可以拥有多个init函数
3. 包的每个源文件也可以拥有多个init函数
4. 同一个包中多个init函数的执行顺序go语言没有明确的定义(说明)
5. 不同包的init函数按照包导入的依赖关系决定该初始化函数的执行顺序
6. init函数不能被其他函数调用，而是在main函数执行之前，自动被调用
*/
func init() {
	fmt.Println("函数初始化内容,会被go自动调用")
}

// Go语言程序的默认入口函数(主函数)：func main()
// main函数只能用于main包中，且只能定义一个。
/*
两个函数的执行顺序：
对同一个go文件的init()调用顺序是从上到下的。
对同一个package中不同文件是按文件名字符串比较“从小到大”顺序调用各文件中的init()函数。
对于不同的package，如果不相互依赖的话，按照main包中"先import的后调用"的顺序调用其包中的init()，如果package存在依赖，则先调用最早被依赖的package中的init()，最后调用main函数。
如果init函数中使用了println()或者print()你会发现在执行过程中这两个不会按照你想象中的顺序执行。
这两个函数官方只推荐在测试环境中使用，对于正式环境不要使用。
*/
func main() { // main函数，是程序执行的入口
	// 在终端打印 Hello World!
	fmt.Println("Hello, World!")

	fmt.Println("\n------数据类型 S数据类型一变量 -----------------------------------------------------------------")
	fmt.Println("\n------数据类型 S数据类型一匿名变量 -----------------------------------------------------------------")
	//在使用多重赋值时，如果想要忽略某个值，可以使用匿名变量
	//匿名变量不占用命名空间，不会分配内存，所以匿名变量之间不存在重复声明。
	_, y, z := S数据类型一变量()
	fmt.Println("y=", y)
	fmt.Println("z=", z)

	S数据类型一常量()

	S数据类型一基本类型()

}

func S数据类型一变量() (int, int, string) {
	// Go语言中的变量需要声明后才能使用，同一作用域内不支持重复声明。
	// 并且Go语言的变量声明后必须使用。

	//变量声明以关键字var开头，变量类型放在变量的后面，行尾无需分号
	var name string

	//批量声明
	var (
		a int = 20
		b int = 30
	)

	//变量赋值 初始化也可以这样赋值
	name, a = "名字", 10

	//未标明数据类型的时候,go编译器会根据等号右边的值来推导变量的类型完成初始化
	var c = 50
	a = c

	//函数内部 也可以直接定义,不需要前置var
	d := 100
	b = d

	//整型和浮点型变量的默认值为0。
	//字符串变量的默认值为空字符串。
	//布尔型变量默认为false。
	//切片、函数、指针变量的默认为nil。
	return a, b, name
}

func S数据类型一常量() {
	fmt.Println("\n------数据类型 S数据类型一常量 -----------------------------------------------------------------")
	/*
		相对于变量，常量是恒定不变的值，多用于定义程序运行期间不会改变的那些值。
		常量的声明和变量声明非常类似，只是把var换成了const，常量在定义的时候必须赋值
		声明了pi和e这两个常量之后，在整个程序运行期间它们的值都不能再发生变化了
	*/
	const pi = 3.1415
	//多个常量可以同时定义
	const (
		p1 = 3.1415
		e  = 2.7182
	)
	//const同时声明多个常量时，如果省略了值则表示和上面一行的值相同。
	const (
		n1 = 100
		n2
		n3
	)
	fmt.Println("\n------数据类型 S数据类型一常量计数器 iota -----------------------------------------------------------------")

	/*
		iota是go语言的常量计数器，只能在常量的表达式中使用。
		iota在const关键字出现时将被重置为0。
		const中每新增一行常量声明将使iota计数一次(iota可理解为const语句块中的行索引)。
		使用iota能简化定义，在定义枚举时很有用。
	*/
	const (
		m1 = iota //0
		m2        //1
		m3        //2
		m4        //3
	)

	//使用_跳过某些值
	const (
		a1 = iota //0
		a2        //1
		_
		a4 //3
	)

	//iota声明中间插队
	const (
		b1 = iota //0
		b2 = 100  //100
		b3 = iota //2
		b4        //3
	)
	const n5 = iota //0

	/*
		定义数量级 （这里的<<表示左移操作，1<<10表示将1的二进制表示向左移10位，也就是由1变成了10000000000，
		也就是十进制的1024。同理2<<2表示将2的二进制表示向左移2位，也就是由10变成了1000，也就是十进制的8。）*/
	const (
		_  = iota
		KB = 1 << (10 * iota)
		MB = 1 << (10 * iota)
		GB = 1 << (10 * iota)
		TB = 1 << (10 * iota)
		PB = 1 << (10 * iota)
	)
	//多个iota定义在一行
	const (
		a, b  = iota + 1, iota + 2 //1,2
		c, d                       //2,3
		e1, f                      //3,4
	)
	fmt.Println(pi, p1, e, n1, n2, n3, m1, m2, m3, m4, a1, a2, a4, b1, b2, b3, b4, n5, KB, MB, GB, TB, PB, a, b, c, d, e1, f)
}

func S数据类型一基本类型() {
	fmt.Println("\n------数据类型 S数据类型一基本类型 -----------------------------------------------------------------")

	/*
		类型				长度(字节)			默认值			说明
		bool			1					false
		byte			1					0				uint8
		rune			4					0				Unicode Code Point, int32
		int, uint		4或8				0				32 或 64 位
		int8, uint8		1					0				-128 ~ 127, 0 ~ 255，byte是uint8 的别名
		int16, uint16	2					0				-32768 ~ 32767, 0 ~ 65535
		int32, uint32	4					0				-21亿~ 21亿, 0 ~ 42亿，rune是int32 的别名
		int64, uint64	8					0
		float32			4					0.0
		float64			8					0.0
		complex64		8
		complex128		16
		uintptr			4或8								以存储指针的 uint32 或 uint64 整数
		array												值类型
		struct												值类型
		string								""				UTF-8 字符串
		slice								nil				引用类型
		map									nil				引用类型
		channel								nil				引用类型
		interface							nil				接口
		function							nil				函数
	*/
	//支持八进制、 六进制，以及科学记数法。标准库 math 定义了各数字类型取值范围
	a, b, c, d := 071, 0x1F, 1e9, math.MinInt16
	fmt.Println(a, b, c, d)
	//空指针值是 nil，而非C/C++ NULL。

	fmt.Println("\n------数值型------")
	/*
	   整型
	   整型分为以下两个大类：
	   按长度分为：int8、int16、int32、int64
	   对应的无符号整型：uint8、uint16、uint32、uint64
	   其中，uint8就是我们熟知的byte型，int16对应C语言中的short型，int64对应C语言中的long型。
	*/
	/*
		浮点型
		Go语言支持两种浮点型数：float32和float64。这两种浮点型数据格式遵循IEEE 754标准： float32 的浮点数的最大范围约为3.4e38，可以使用常量定义：math.MaxFloat32。 float64 的浮点数的最大范围约为 1.8e308，可以使用一个常量定义：math.MaxFloat64。
	*/
	/*
		复数
		complex64和complex128
	*/
	var c1 complex64
	c1 = 1 + 2i
	var c2 complex128
	c2 = 2 + 3i
	fmt.Println(c1)
	fmt.Println(c2)
	//复数有实部和虚部，complex64的实部和虚部为32位，complex128的实部和虚部为64位。
	fmt.Println("\n------布尔型------")
	/*
		Go语言中以bool类型进行声明布尔型数据，布尔型数据只有true（真）和false（假）两个值。
		注意：
		    布尔类型变量的默认值为false。
		    Go 语言中不允许将整型强制转换为布尔型。
		    布尔型无法参与数值运算，也无法与其他类型进行转换。
	*/
	fmt.Println("\n------字符串型------")
	/*
		字符串
		Go语言中的字符串以原生数据类型出现，使用字符串就像使用其他原生数据类型（int、bool、float32、float64 等）一样。 Go 语言里的字符串的内部实现使用UTF-8编码。
		字符串的值为双引号("")中的内容，可以在Go语言的源码中直接添加非ASCII码字符，例如：
	*/
	s1 := "hello"
	s2 := "你好"
	fmt.Println(s1, s2)

	/*
		字符串转义符
		Go 语言的字符串常见转义符包含回车、换行、单双引号、制表符等，如下表所示。

		转义	含义
		\r	回车符（返回行首）
		\n	换行符（直接跳到下一行的同列位置）
		\t	制表符
		\'	单引号
		\"	双引号
		\	反斜杠
	*/

	//案例 打印路径
	fmt.Println("str := \"c:\\pprof\\main.exe\"")
	//str := "c:\pprof\main.exe"

	fmt.Println("\n------字符串加深------")
	//多行字符串
	//Go语言中要定义一个多行字符串时，就必须使用反引号字符：
	//反引号间换行将被作为字符串中的换行，但是所有的转义字符均无效，文本将会原样输出。
	s5 := `多行字符串 第一行
		第二行
		第三行
		`
	fmt.Println(s5)

	fmt.Println("\n------字符串的常用操作------")
	//len(str)								求长度
	//+或fmt.Sprintf						拼接字符串
	//strings.Split							分割
	//strings.contains						判断是否包含
	//strings.HasPrefix,strings.HasSuffix	前缀/后缀判断
	//strings.Index(),strings.LastIndex()	子串出现的位置
	//strings.Join(a[]string, sep string)	join操作

	fmt.Println("\n------byte和rune类型------")
	//组成每个字符串的元素叫做“字符”，可以通过遍历或者单个获取字符串元素获得字符。
	//字符用单引号（' '）包裹起来，如：
	var a6 = '中'
	var b6 = 'x'
	//Go 语言的字符有以下两种：
	//
	//uint8类型，或者叫 byte 型，代表了ASCII码的一个字符。
	//rune类型，代表一个 UTF-8字符。
	//当需要处理中文、日文或者其他复合字符时，则需要用到rune类型。
	//rune类型实际是一个int32。
	//Go 使用了特殊的 rune 类型来处理 Unicode，
	//让基于 Unicode的文本处理更为方便，也可以使用 byte 型进行默认字符串处理，性能和扩展性都有照顾
	//遍历字符串
	s := "pprof.cn博客"
	for i := 0; i < len(s); i++ { //byte
		fmt.Printf("%v(%c) ", s[i], s[i])
	}
	fmt.Println()
	for _, r := range s { //rune
		fmt.Printf("%v(%c) ", r, r)
	}
	//因为UTF8编码下一个中文汉字由3~4个字节组成，所以我们不能简单的按照字节去遍历一个包含中文的字符串，否则就会出现上面输出中第一行的结果。
	//
	//字符串底层是一个byte数组，所以可以和[]byte类型相互转换。字符串是不能修改的，字符串是由byte字节组成，所以字符串的长度是byte字节的长度。
	//rune类型用来表示utf8字符，一个rune字符由一个或多个byte组成。

	fmt.Println(a6, b6)

	fmt.Println("\n------修改字符串------")
	//要修改字符串，需要先将其转换成[]rune或[]byte，完成后再转换为string。
	//无论哪种转换，都会重新分配内存，并复制字节数组。
	s1 = "hello"
	// 强制类型转换
	byteS1 := []byte(s1)
	byteS1[0] = 'H'
	fmt.Println(string(byteS1))

	s2 = "博客"
	runeS2 := []rune(s2)
	runeS2[0] = '狗'
	fmt.Println(string(runeS2))

	fmt.Println("\n------类型转换------")
	//Go语言中只有强制类型转换，没有隐式类型转换。
	//
	//该语法只能在两个类型之间支持相互转换的时候使用。
	//
	//强制类型转换的基本语法如下：
	//
	//T(表达式)
	//其中，T表示要转换的类型。表达式包括变量、复杂算子和函数返回值等.
	//
	//比如计算直角三角形的斜边长时使用math包的Sqrt()函数，该函数接收的是float64类型的参数，而变量a和b都是int类型的，这个时候就需要将a和b强制类型转换为float64类型。
	var a7, b7 = 3, 4
	var c7 int
	/* math.Sqrt()接收的参数是float64类型，需要强制转换*/
	c7 = int(math.Sqrt(float64(a7*a7 + b7*b7)))
	fmt.Println(c7)
}

//fmt.Println("\n------S数据类型一匿名变量 -----------------------------------------------------------------")
