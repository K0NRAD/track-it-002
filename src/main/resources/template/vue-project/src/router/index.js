import { createRouter , createWebHistory} from  'vue-router'
import StartPage from '../views/StartPage.vue'
import Tracker from '../views/TimeTrackerMenu.vue'

const routes = [
    {
        path: '/',
        name: 'StartPage',
        component: StartPage
    },
    {
        path: '/trackIt',
        name: 'Tracker',
        component: Tracker
    }
]

const router = createRouter(
    {
        history: createWebHistory(import.meta.env.BASE_URL),
        routes
    }
)

export default router