<template>
  <div class="bg">
    <img src="../assets/ice.jpg"  class="img">
    <div class="login">
    <mylogin ></mylogin>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import mylogin from '@/components/mylogin.vue'

export default {
  name: 'Home',
  components: {
    mylogin
  }
}
</script>
<style scoped>
.bg{
  width: 0%;
  height: 0%;
  position: relative;

}
.img{
  opacity: 40%;

  position: fixed;
}
.login{
  border:double red;
  width: 300px;
  height: 400px;

  margin:  auto;
}

</style>
