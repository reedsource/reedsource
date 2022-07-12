package main // 声明 main 包，表明当前是一个可执行程序

import "fmt" // 声明 main 包，表明当前是一个可执行程序

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

//fmt.Println("\n------S数据类型一匿名变量 -----------------------------------------------------------------")
