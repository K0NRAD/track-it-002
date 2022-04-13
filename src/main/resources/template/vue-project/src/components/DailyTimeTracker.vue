<script>
var today = new Date();
const loadDate = () => {
    
    document.getElementById('dateElement').innerHTML = today.getDate() + '/' + today.getMonth() + '/' + today.getFullYear();
    //timeElement.textContent = today.getHours() + ':' + today.getMinutes() + ':' + today.getSeconds();;
}
window.setInterval(loadDate, 100)

const displayTime= ()=>{
    var dateTime = new Date();
    var hrs = dateTime.getHours();
    var min = dateTime.getMinutes();

    document.getElementById('hours').innerHTML = hrs;
    document.getElementById('minutes').innerHTML = min;

}
setInterval(displayTime, 10);

</script>
<template>
<div class="card-container" id= "dailyTracker" >
    <div class="dateTime-Container">
        <div class="date-row">
            <span id="dateElement" ></span>
        </div>
        <div class="time-row">
           <span id="hours"></span>
                    <span>:</span>
                    <span id="minutes"></span>
        </div>
    </div>
    <div class="userInformation">
        <div class="profilPic">

        </div>
        <div class="userName">
            Annmarie Ihrke
        </div>
        <div class="personnelNumber">
            	817492791
        </div>
    </div>
    
    <div class="tracker">
        <div class="checkIn">
            
        </div>
        <div class="workTime">

        </div>
        <div class="breakTime">

        </div>
    </div>
    <div class="button-RowCenter">
        <button class="checkIn-Button">Check-In</button>
        <button class="break-Button">Break</button>
        <button class="checkOut-Button">Check-Out</button>
    </div>

</div>

</template>
<style>
.card-container{
    width: 20rem;
    height: 100%;
    border-radius: 1rem;
    margin-right: 1.5rem;
    padding: 0.5rem;
    background: linear-gradient(rgba(0, 0, 0, 0.25), rgba(0, 0, 0, 0.25));
}
.card-container:hover{
    background: #ffffff;
    cursor: pointer;
    
}
.card-container button{
    background: grey;
}


</style>