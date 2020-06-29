<template>
<div class="home-div">
  <div class="home-login"><mylogin></mylogin></div>

</div>
</template>

<script>
// @ is an alias to /src
import mylogin from '@/components/mylogin.vue'

export default {
  name: 'Home',
  components: {
    mylogin
  },
    beforeCreate () {

  document.querySelector('body').setAttribute('style', 'background-color:#E1F2ED')
    },
  beforeDestroy () {

  document.querySelector('body').removeAttribute('style')

}
  }

</script>
<style scoped>
.home-div{
  display: flex;
  flex-direction: column;

}
.home-login{
  width: 50vh;
  margin: 20vh auto;
}



</style>
