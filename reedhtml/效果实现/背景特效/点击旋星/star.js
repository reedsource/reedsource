/**
 * Created by Administrator on 2017/6/17 0017.
 */
(function () {
    //设置画布,
    var canvasEl = document.getElementById('canvas');
    var ctx = canvasEl.getContext('2d');
    //var mousePos = [0, 0];
    var mousePos = [canvasEl.width/2, canvasEl.height/2];

    var easingFactor = 5.0;
    var backgroundColor = '#000';//背景色
    var nodeColor = '#fff';//线条和节点的颜色
    var edgeColor = '#fff';

    var nodes = [];//节点数组
    var edges = [];//线条数组

    function constructNodes() {//用函数来构建节点
        for (var i = 0; i < 100; i++) {
            var node = {
                drivenByMouse: i == 0,//实现鼠标跟随效果
                //x y随机产生,并乘以画布的宽高,使得点和线可以在画布范围内随机出现,而不只是从0,0 出发
                x: Math.random() * canvasEl.width,
                y: Math.random() * canvasEl.height,

               /* 这样子会让所有的点和线都从0开始
                x: Math.random() ,
                y: Math.random() ,
                */
                vx: Math.random() * 1 - 0.5,
                vy: Math.random() * 1 - 0.5,
                //对随机生成的半径的大小进行判断,结果有两种情况,这就形成了不同大小的点
                radius: Math.random() > 0.9 ? 3 + Math.random() * 3 : 1 + Math.random() * 3
            };

            nodes.push(node);
        }
        //连线 因为要把两个点连成一条线,所以需要把两个点捆绑到一起,因此用的双重for循环  e代表的是点
        nodes.forEach(function (e) {
            nodes.forEach(function (e2) {
                if (e == e2) {//如果两个点是一样的,就结束.也就是说不用划线
                    return;
                }

                var edge = {//否则,就划线,从起点到终点
                    from: e,
                    to: e2
                }

                addEdge(edge);
            });
        });
    }

    function addEdge(edge) {//添加节流阀,如果点和线重合就不画线
        var ignore = false;

        edges.forEach(function (e) {
            if (e.from == edge.from && e.to == edge.to) {
                ignore = true;
            }

            if (e.to == edge.from && e.from == edge.to) {
                ignore = true;
            }
        });

        if (!ignore) {
            edges.push(edge);
        }
    }

    function step() {
        nodes.forEach(function (e) {
            //对节点进行遍历,如果某个点是由鼠标驱动的,就返回.也就是说让这个点不运动
            if (e.drivenByMouse) {
                return;
            }
            //v是速度
            e.x += e.vx;
            e.y += e.vy;

            function clamp(min, max, value) {
                if (value > max) {
                    return max;
                } else if (value < min) {
                    return min;
                } else {
                    return value;
                }
            }

            if (e.x <= 0 || e.x >= canvasEl.width) {
                e.vx *= -1;
                e.x = clamp(0, canvasEl.width, e.x)
            }

            if (e.y <= 0 || e.y >= canvasEl.height) {
                e.vy *= -1;
                e.y = clamp(0, canvasEl.height, e.y)
            }
        });

        adjustNodeDrivenByMouse();
        render();
        window.requestAnimationFrame(step);
    }

    function adjustNodeDrivenByMouse() {
        nodes[0].x += (mousePos[0] - nodes[0].x) / easingFactor;
        nodes[0].y += (mousePos[1] - nodes[0].y) / easingFactor;
    }

    function lengthOfEdge(edge) {//算线的长度 math.sqrt(x);指的是算对x开平方. math.pow(x,y); 求的是x的y次方的值
        return Math.sqrt(Math.pow((edge.from.x - edge.to.x), 2) + Math.pow((edge.from.y - edge.to.y), 2));//根据直角三角形的斜边等于两个直角边之和开平方 算出两点之间的边长
    }

    function render() {
        ctx.fillStyle = backgroundColor;
        ctx.fillRect(0, 0, canvasEl.width, canvasEl.height);

        edges.forEach(function (e) {
            var l = lengthOfEdge(e);
            var threshold = canvasEl.width / 8;//控制线长 如果过于小,线就会消失

            if (l > threshold) {
                return;
            }

            ctx.strokeStyle = edgeColor;
            ctx.lineWidth = (1.0 - l / threshold) * 2.5;
            ctx.globalAlpha = 1.0 - l / threshold;
            ctx.beginPath();
            ctx.moveTo(e.from.x, e.from.y);
            ctx.lineTo(e.to.x, e.to.y);
            ctx.stroke();
        });
        ctx.globalAlpha = 1.0;//canvas中透明度设置
        //ctx.globalAlpha = 0.5;

        nodes.forEach(function (e) {
            if (e.drivenByMouse) {
                return;
            }

            ctx.fillStyle = nodeColor;
            ctx.beginPath();
            ctx.arc(e.x, e.y, e.radius, 0, 2 * Math.PI);
            ctx.fill();
        });
    }

    window.onresize = function () {//窗口改变
        canvasEl.width = document.body.clientWidth;
        canvasEl.height = canvasEl.clientHeight;

        if (nodes.length == 0) {
            constructNodes();
        }

        render();
    };
    //e
    window.onmousemove = function (e) {//鼠标跟随
        mousePos[0] = e.clientX;
        mousePos[1] = e.clientY;
    }

    window.onresize(); // trigger the event manually.
    window.requestAnimationFrame(step);
}).call(this);