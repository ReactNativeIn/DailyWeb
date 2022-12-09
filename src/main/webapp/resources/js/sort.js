/**
 *
 */

// 정렬방식 눌렀을때
function sort(list) {
  var Sort = list;	// 받아올 인자
  
  var link = decodeURI(document.location.href); // 현재 url, decodeURI = GET해올때 한글깨짐 방지

  if (link.includes("new")) {
    location.href = "/DailyWeb/list/new?list=" + Sort;
  } else if (link.includes("남녀공용")) {
    location.href = "/DailyWeb/list/남녀공용?list=" + Sort;
  }

}
