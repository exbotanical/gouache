<script lang="ts" setup>
import { useCredentials } from '@/hooks'
import { authApi, ErroneousResponseError, useErrorHandler } from '@/services'
import { useSessionStore } from '@/state'
import { required } from '@/utils'
import { Loading } from 'quasar'

const $router = useRouter()
const sessionStore = useSessionStore()
const { formModel, shouldDisable } = useCredentials()

async function handleSubmitLogin() {
  Loading.show()
  try {
    const { ok } = await authApi.login(formModel)

    if (!ok) {
      throw new ErroneousResponseError('Invalid credentials.')
    }

    sessionStore.setUserState(formModel)
    $router.push({ name: 'Dashboard' })
  } catch (ex) {
    useErrorHandler(ex, {
      notify: true,
      fallback: 'Unable to login.',
    })
  } finally {
    Loading.hide()
  }
}
</script>

<template>
  <q-card style="width: 400px">
    <q-form class="q-pa-md" @submit.prevent>
      <q-card-section>
        <div class="text-h6">@todo logo</div>
      </q-card-section>

      <q-card-section>
        <q-input
          label="Username"
          v-model="formModel.username"
          filled
          dense
          class="q-mb-md"
          autocomplete="username"
          :rules="[required('A username is required.')]"
        />
        <q-input
          label="Password"
          v-model="formModel.password"
          filled
          type="password"
          autocomplete="current-password"
          dense
          class="q-mb-md"
          :rules="[required('A password is required.')]"
        />
      </q-card-section>

      <q-card-actions class="justify-between">
        <q-btn
          label="Register"
          unelevated
          color="primary"
          outline
          @click="$router.push({ name: 'Register' })"
        />
        <div>
          <q-btn
            label="Login"
            type="submit"
            unelevated
            color="primary"
            :disable="shouldDisable"
            @click="handleSubmitLogin"
          />
          <q-tooltip v-if="shouldDisable">
            You must provide a username and password to login.
          </q-tooltip>
        </div>
      </q-card-actions>
    </q-form>
  </q-card>
</template>