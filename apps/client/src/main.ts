import { createApp } from 'vue'

import App from './App.vue'

import router from '@/router'
import { debugPlugin, registerNotifyPlugin } from '@/plugins'
import { Dialog, Loading, Notify, Quasar } from 'quasar'
import quasarIconSet from 'quasar/icon-set/svg-mdi-v6'
import '@quasar/extras/mdi-v6/mdi-v6.css'
import 'quasar/src/css/index.sass'

createApp(App)
  .use(debugPlugin)
  .use(registerNotifyPlugin)
  .use(Quasar, {
    config: {
      brand: {
        'primary': '#36608a',
        'secondary': '#6fa8a3',
        'accent': '#c9bacc',

        'dark': '#454343',
        'dark-page': '#545454',

        'positive': '#87ed9f',
        'negative': '#d9626f',
        'info': '#4fafc2',
        'warning': '#e6c467',
      },
      loading: {},
      notify: {},
    },
    iconSet: quasarIconSet,
    plugins: [Dialog, Loading, Notify],
  })
  .use(router)
  .mount('#app')
