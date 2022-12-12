/**
 *
 */

// 정렬방식 눌렀을때
function sort(list) {
  var Sort = list;	// 받아올 인자
  
  var link = decodeURI(document.location.href); // 현재 url, decodeURI = GET해올때 한글깨짐 방지

  if (link.includes("new")) {
    location.href = "/product/new?list=" + Sort;
  } else if (link.includes("남녀공용")) {
    location.href = "/product/남녀공용?list=" + Sort;
  } else if (link.includes("남성")) {
    location.href = "/product/남성?list=" + Sort;
  } else if (link.includes("여성")) {
    location.href = "/product/여성?list=" + Sort;
  }
}
