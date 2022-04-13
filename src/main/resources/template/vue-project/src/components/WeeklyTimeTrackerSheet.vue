<script>
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
        <div class="container" >
            <div class="date-row">
            <span id="dateElement" ></span>
        </div>
        <div class="time-row">
           <span id="hours"></span>
                    <span>:</span>
                    <span id="minutes"></span>
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

                </div>
                <div class="checkOut">

                </div>
                <div class="workTime">

                </div>
                <div class="break">

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