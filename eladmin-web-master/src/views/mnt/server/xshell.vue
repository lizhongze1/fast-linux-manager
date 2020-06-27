<template>
  <div id="terminal" style="margin:30px 0 0 0;height:300px;width:auto" />
</template>
<script>
import { Terminal } from 'xterm'
import 'xterm/css/xterm.css'
import 'xterm/lib/xterm.js'
var term = new Terminal({
  cursorBlink: true, // 光标闪烁
  cursorStyle: 'block', // 光标样式  null | 'block' | 'underline' | 'bar'
  scrollback: 800, // 回滚
  tabStopWidth: 8, // 制表宽度
  screenKeys: true
})
export default {

  created() {
    this.fetchData()
  },
  mounted() {
    term.open(document.getElementById('terminal'))
    // ondta方法是terminal获取输入数据的方法，因此要在这里给后台发送数据
    term.onData(function(data) {
      // 键盘输入时的回调函数
      console.log(data)
    })
  },
  methods: {
    fetchData() {
        console.log(this.$route.query.id);
/*      nodeServerLog(this.$route.query.id).then(res => {
        this.form.desc = res.data
      })*/
    },
    onRefresh() {
      this.fetchData()
    },
    onBack() {
      history.go(-1)
    }
  }
}
</script>
