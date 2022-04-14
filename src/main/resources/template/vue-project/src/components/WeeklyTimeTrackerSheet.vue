<script>
var today = new Date();
const loadDate = () => {
    
    document.getElementById('dateElement3').innerHTML = today.getDate() + '/' + (today.getMonth()+1) + '/' + today.getFullYear();
}
window.setInterval(loadDate, 100)

const displayTime= ()=>{
    var dateTime = new Date();
    var hrs = dateTime.getHours();
    var min = dateTime.getMinutes();

    document.getElementById('hours3').innerHTML = hrs ;
    document.getElementById('minutes3').innerHTML = min;

}
setInterval(displayTime, 10);

export default{
    methods: {
        PrintPdf(){
            window.print();
        }
    }
}


</script>
<template>
<div>
    <div class="card-container" id="weeklyTimeSheet" >
        <div class="dateTime-Container" >
            <div class="date-row">
                <span id="dateElement3" ></span>
            </div>
            <div class="time-row">
                <span id="hours3"></span>
                <span>:</span>
                <span id="minutes3"></span>
            </div>
        </div>
        <div class="container">
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
        </div>
        <div class="container">
            <div class="workTimeInformation">
            <!-- maybe forEach again!? -->
                <div class="dates">

                </div>
                <div class="checkIn">
                    <span>Check-In</span>
                    <span></span>
                </div>
                <div class="checkOut">
                    <span>Check-Out:</span>
                    <span></span>
                </div>
                <div class="workTime">
                    <span>worked Time:</span>
                    <span></span>
                </div>
                <div class="break">
                    <span>total break Time:</span>
                    <span></span>
                </div>
            </div>
        </div>
        <div class="button-Row">
            <button class="printButton" @click="PrintPdf">Print</button>
        </div>
    </div>
</div>
</template>
<style>
.card-container:hover .printButton {
    background: #a05aad;
}
    
</style>