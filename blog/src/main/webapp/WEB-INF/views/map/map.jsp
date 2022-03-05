<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div id="map" style="width: 100%; height: 100vh;"></div>

<script src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=0c1e20f028c08bccaed64aed120cc2a2&libraries=clusterer"></script>
<script>
$(document).ready(function(){ 
	$.post('/blog/api/map/list', null, function(rs){
		createMap(rs[0]);
	});
});

	
function createMap(param) {
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = {
		center : new kakao.maps.LatLng(param.LA_CRD, param.LO_CRD), // 지도의 중심좌표
		level : 3, // 지도의 확대 레벨
		mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도 종류
	// 지도종류
	};

	// 지도를 생성한다 
	var map = new kakao.maps.Map(mapContainer, mapOption);

	// 마커 클러스터러를 생성합니다 
	var clusterer = new kakao.maps.MarkerClusterer({
		map : map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
		averageCenter : true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
		minLevel : 10
	// 클러스터 할 최소 지도 레벨 
	}); 

	/* var place = [
			[ 35.23394549882176, 129.08108688954226,
					'<div style="padding:5px;">부산대<div>' ],
			[ 35.23264907994035, 129.08433178009744,
					'<div style="padding:5px;">NC백화점 부산대점<div>' ],
			[ 35.23093019897989, 129.08870597906775,
					'<div style="padding:5px;">부산대<div>' ] ]; */

	var places = JSON.parse(param.GEOM_JSON).coordinates[0];
	var place = [];
	$.each(places, function(idx, item){
		let tmp = [];
		tmp.push(item[1]);
		tmp.push(item[0]);
		tmp.push('<div style="padding:5px;">' + param.SIDO_SGG_NM + '</div>')
		place.push(tmp);
	});
	
	console.log(place)
	var markers = [];

 	for (var i = 0; i < place.length; i++) {
		// 마커가 표시될 위치입니다 
		var markerPosition = new kakao.maps.LatLng(place[i][0], place[i][1]);

		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
			position : markerPosition, 
			map: map
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);

		iwPosition = new kakao.maps.LatLng(place[i][0], place[i][1]); //인포윈도우 표시 위치입니다

		// 인포윈도우를 생성합니다
		var infowindow = new kakao.maps.InfoWindow({
			position : iwPosition,
			content : place[i][2]
		});

		// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
		infowindow.open(map, marker);
		markers.push(marker);

		// 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
		// 이벤트 리스너로는 클로저를 만들어 등록합니다 
		// for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
		kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
		kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
	}

	// 클러스터러에 마커들을 추가합니다
	clusterer.addMarkers(markers);

	// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
	function makeOverListener(map, marker, infowindow) {
		return function() {
			infowindow.open(map, marker);
		};
	}

	// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
	function makeOutListener(infowindow) {
		return function() {
			infowindow.close();
		};
	}
}
  	
</script>

<%@ include file="../layout/footer.jsp"%>
	