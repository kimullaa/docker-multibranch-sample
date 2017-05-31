<template>
  <div class="todolist">
    <h1>Todo List</h1>
    <ul>
      <li v-for="todo in todos">
        {{todo.message}}
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'todolist',
  mounted: function () {
    axios.get('/todos').then(function (response) {
      this.todos = response.data
    }).catch(function (err) {
      console.log(err)
    })
  },
  data () {
    return {
      todos: [
        {
          id: 1,
          message: 'sample xxx',
          done: true
        },
        {
          id: 2,
          message: 'sample yyy',
          done: false
        },
        {
          id: 3,
          message: 'sample zzz',
          done: true
        }
      ]
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
ul {
  list-style-type: none;
  font-size: large
}
li {
  padding-bottom: 10px;
}
</style>
