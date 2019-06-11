

export const linkTo = function(obj){
  console.log(obj)
  this.$router.push(obj);
}

export const replaceTo = function(obj){
  console.log(obj);
  this.$router.replace(obj)
}