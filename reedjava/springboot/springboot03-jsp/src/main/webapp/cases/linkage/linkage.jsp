<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/tags/taglib.jsp" %>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Title</title>
	<script type="text/javascript">
        $(document).ready(function () {
            var provinceName = "${linkage.provinceName}";
            /*初始化省份*/
            $.ajax({
                type: "post",
                url: "/cases/linkage/province",
                dataType: "json",
                success: function (data) {
                    var provinceMsg = '';
                    $.each(data, function (i, item) {
                        provinceMsg = provinceMsg + '<option value=' + item.provinceId + '>' + item.provinceName + '</option>';
                        if (item.provinceName === provinceName) {
                            getCity(item.provinceId)
                        }
                    });
                    //开卡省份
                    $('#cardProvinceSel').append(provinceMsg);
                }
            });

            $('#cardProvinceSel').change(function () {
                var provinceCode = $(this).find('option:selected').attr('value');
                $("#cardCitySel").empty();
                //本处两种写法 个别情况第二种无法使用
                $("#s2id_cardCitySel .select2-chosen").html
                // $("#cardCitySel").append('<option  value=--请选择市-- >--请选择市--</option>');
                getCity(provinceCode);
            });
        });

        function getCity(provinceCode) {
            $.ajax({
                type: "post",
                dataType: "json",
                url: "/cases/linkage/city?provinceId=" + provinceCode,
                success: function (data) {
                    var cityMsg = '';
                    $.each(data, function (i, item) {
                        cityMsg = cityMsg + '<option value=' + item.cityId + '>' + item.cityName + '</option>';
                    });
                    $("#cardCitySel").append(cityMsg);
                },
                error: function () {
                    console.log("fail");
                }
            });
        }
	</script>
</head>
<body>
<div style="width: 400px;position:relative;top: 50px;">
	<form:form id="inputForm" modelAttribute="linkage" action="" method="post">
		<div class="form-group">
			<label class="control-label col-sm-3">开户省：</label>
			<div>
				<form:select path="provinceName" id="cardProvinceSel" class="form-control">
					<%--如果传入的省名称不为空 特殊保存省名称情况--%>
					<c:if test="${linkage.provinceName ne null}">
						<%--本处假定只传入了省名称,通过省名称 通过tlds配置调用工具类--%>
						<form:option value="${fns:getProvinceId(linkage.provinceName)}"
						             label="${linkage.provinceName}"/>
					</c:if>
					<form:option value="" label="--请选择省--"/>
				</form:select>
			</div>
			<br>
			<label class="control-label col-sm-3">开户市：</label>
			<div>
				<form:select path="cityName" id="cardCitySel" class="form-control">
					<c:if test="${linkage.cityName ne null}">
						<form:option value="${fns:getCityId(linkage.cityName)}" label="${linkage.cityName}"/>
					</c:if>
					<form:option value="" label="--请选择市--"/>
				</form:select>
			</div>
		</div>
	</form:form>
</div>
</body>
</html>
