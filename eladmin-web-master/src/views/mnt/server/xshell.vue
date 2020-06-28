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
      term.write(data);
      console.log(data)
    })
  },
  methods: {
    fetchData() {
      console.log(this.$route.query.id)
    },
    onRefresh() {
      this.fetchData()
    },
    onBack() {
      history.go(-1)
    }
  }
}



function (ip) {
    if (window.WebSocket) {
        //如果支持websocket
        this._connection = new WebSocket(ip);
    }else {
        //否则报错
        console.log('WebSocket Not Supported');
        return;
    }
    //onpen、onmessage等都是WebSocket类自带的监听方法
    this._connection.onopen = function () {
        console.log('WebSocket estblished');
    };

    this._connection.onmessage = function (evt) {
        var data = evt.data.toString();
        //	接收到客户端返回的信息
        console.log('WebSocket data',data);
    };
    //send方法往后台发送数据
    this._connection.send(JSON.stringify(data));

    this._connection.onerror = function (evt) {
        var url= evt.currentTarget.url.toString();
        console.log(url,' is Rejected Acessed');
    };

    this._connection.onclose = function (evt) {
        console.log('WebSocket Closed');
    };
}
</script>
