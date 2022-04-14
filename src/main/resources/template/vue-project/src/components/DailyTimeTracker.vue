<script>
var today = new Date();
const loadDate = () => {
    
    document.getElementById('dateElement').innerHTML = today.getDate() + '/' + (today.getMonth()+1) + '/' + today.getFullYear();
}
window.setInterval(loadDate, 100)

const displayTime= ()=>{
    var dateTime = new Date();
    var hrs = dateTime.getHours();
    var min = dateTime.getMinutes();

    document.getElementById('hours').innerHTML = hrs ;
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
.card-container:hover .checkIn-Button{
    background-color: #86f075;
}
.card-container:hover .break-Button{
    background-color: #fcfa74;  
}
.card-container:hover .checkOut-Button{
    background-color: #fc7262;
}
.dateTime-Container {
    margin-top: 2rem;
    display: flex
    ;  
}
.time-row{
    width: 40%;
    text-align: right;
}
.date-row{
    width: 50%;
    margin: 0;
    padding-left: 0.5rem;
    
}
.button-RowCenter button{
     width: 20rem;
        padding: 1.5rem;
        margin: 0.5rem 0 0.5rem 0;
        border-radius: 1.5rem;
        border: none;
}


</style>