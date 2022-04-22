<script>
try{
    var today = new Date();
    const loadDate = () => {
        document.getElementById('dateElement2').innerHTML = today.getDate() + '/' + (today.getMonth()+1) + '/' + today.getFullYear();
    }
    window.setInterval(loadDate, 1000);

    const displayTime = () => {
        var dateTime = new Date();
        var hrs = dateTime.getHours();
        var min = dateTime.getMinutes();

        document.getElementById('hours2').innerHTML = hrs ;
        document.getElementById('minutes2').innerHTML = min;
    }
    setInterval(displayTime, 1000);
}catch{
}

</script>
<template>
    <div class="card-container" id="protocolForm" >
        <div class="dateTime-Container">
           <div class="date-row">
                <span id="dateElement2" ></span>
            </div>
            <div class="time-row">
                <span id="hours2"></span>
                <span>:</span>
                <span id="minutes2"></span>
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
        <div class="weekIdentifier">
            <div class="calenderWeek"> 

            </div>
            <div class="startDay">

            </div>
            <div class="endDay">

            </div>
        </div>
        <div class="protocolForm">
            <!-- putting into forEach!?
                wenn Bearbeitungsmodus -> active -> enable input-Felder
            -->
            <div class="dates">

            </div>
            <div class="times">

            </div>
            <div class="type">

            </div>
            <div class="description">

            </div>
        </div>
        <div class="button-Row">
            <button class="printButton">Print</button>
            <button class="sendButton">Send</button>
        </div>
    </div>
    </div>
</template>
<style>

.button-Row{
    float: right;
    
}
.button-Row button{
     width: 20rem;
        padding: 1.5rem;
        margin: 0.5rem 0 0.5rem 0;
        border-radius: 1.5rem;
        border: none;
}
.card-container:hover .sendButton {
    background: #2ea652;
}
</style>