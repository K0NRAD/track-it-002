<script>

</script>
<template>
    <div class="card-container" id="protocolForm" >
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
.card-container:hover .sendButton {
    background: #2ea652;
}
</style>