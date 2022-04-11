today = new Date();




var timeElement = document.getElementById('timeElement')



//const loadTime = () => {
    
//    document.getElementById('timeElement').innerHTML = today.getHours() + ':' + today.getMinutes() + ':' + today.getSeconds();
    //timeElement.textContent = today.getHours() + ':' + today.getMinutes() + ':' + today.getSeconds();;
//}
const loadDate = () => {
    
    document.getElementById('dateElement').innerHTML = today.getDate() + '/' + today.getMonth() + '/' + today.getFullYear();
    //timeElement.textContent = today.getHours() + ':' + today.getMinutes() + ':' + today.getSeconds();;
}


//window.setInterval(loadTime, 100);
window.setInterval(loadDate, 100)

const displayTime= ()=>{
    var dateTime = new Date();
    var hrs = dateTime.getHours();
    var min = dateTime.getMinutes();
    var sec = dateTime.getSeconds();

    document.getElementById('hours').innerHTML = hrs;
    document.getElementById('minutes').innerHTML = min;
    document.getElementById('seconds').innerHTML = sec;

}
setInterval(displayTime, 10);


