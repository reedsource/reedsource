/**
 * Created by Administrator on 2017/6/17 0017.
 */
(function () {
    //���û���,
    var canvasEl = document.getElementById('canvas');
    var ctx = canvasEl.getContext('2d');
    //var mousePos = [0, 0];
    var mousePos = [canvasEl.width/2, canvasEl.height/2];

    var easingFactor = 5.0;
    var backgroundColor = '#000';//����ɫ
    var nodeColor = '#fff';//�����ͽڵ����ɫ
    var edgeColor = '#fff';

    var nodes = [];//�ڵ�����
    var edges = [];//��������

    function constructNodes() {//�ú����������ڵ�
        for (var i = 0; i < 100; i++) {
            var node = {
                drivenByMouse: i == 0,//ʵ��������Ч��
                //x y�������,�����Ի����Ŀ��,ʹ�õ���߿����ڻ�����Χ���������,����ֻ�Ǵ�0,0 ����
                x: Math.random() * canvasEl.width,
                y: Math.random() * canvasEl.height,

               /* �����ӻ������еĵ���߶���0��ʼ
                x: Math.random() ,
                y: Math.random() ,
                */
                vx: Math.random() * 1 - 0.5,
                vy: Math.random() * 1 - 0.5,
                //��������ɵİ뾶�Ĵ�С�����ж�,������������,����γ��˲�ͬ��С�ĵ�
                radius: Math.random() > 0.9 ? 3 + Math.random() * 3 : 1 + Math.random() * 3
            };

            nodes.push(node);
        }
        //���� ��ΪҪ������������һ����,������Ҫ������������һ��,����õ�˫��forѭ��  e������ǵ�
        nodes.forEach(function (e) {
            nodes.forEach(function (e2) {
                if (e == e2) {//�����������һ����,�ͽ���.Ҳ����˵���û���
                    return;
                }

                var edge = {//����,�ͻ���,����㵽�յ�
                    from: e,
                    to: e2
                }

                addEdge(edge);
            });
        });
    }

    function addEdge(edge) {//��ӽ�����,���������غϾͲ�����
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
            //�Խڵ���б���,���ĳ�����������������,�ͷ���.Ҳ����˵������㲻�˶�
            if (e.drivenByMouse) {
                return;
            }
            //v���ٶ�
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

    function lengthOfEdge(edge) {//���ߵĳ��� math.sqrt(x);ָ�������x��ƽ��. math.pow(x,y); �����x��y�η���ֵ
        return Math.sqrt(Math.pow((edge.from.x - edge.to.x), 2) + Math.pow((edge.from.y - edge.to.y), 2));//����ֱ�������ε�б�ߵ�������ֱ�Ǳ�֮�Ϳ�ƽ�� �������֮��ı߳�
    }

    function render() {
        ctx.fillStyle = backgroundColor;
        ctx.fillRect(0, 0, canvasEl.width, canvasEl.height);

        edges.forEach(function (e) {
            var l = lengthOfEdge(e);
            var threshold = canvasEl.width / 8;//�����߳� �������С,�߾ͻ���ʧ

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
        ctx.globalAlpha = 1.0;//canvas��͸��������
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

    window.onresize = function () {//���ڸı�
        canvasEl.width = document.body.clientWidth;
        canvasEl.height = canvasEl.clientHeight;

        if (nodes.length == 0) {
            constructNodes();
        }

        render();
    };
    //e
    window.onmousemove = function (e) {//������
        mousePos[0] = e.clientX;
        mousePos[1] = e.clientY;
    }

    window.onresize(); // trigger the event manually.
    window.requestAnimationFrame(step);
}).call(this);