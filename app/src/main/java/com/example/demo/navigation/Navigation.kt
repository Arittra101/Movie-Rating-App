package  com.example.demo.navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavInflater
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.demo.R
import java.util.Stack

object Navigation {

    private val popBackStack = Stack<Int>()
    private var homeDestination : Int? = null
    private var CurrentDestination : Int? = null

    //this method helps in fragment to fragment navigation
    fun navigate(fragment: Fragment, popBackFragment: Int? = null, to: Int, bundle: Bundle? = null) {
        val navController = fragment.findNavController()
        val navOptionsBuilder = NavOptions.Builder()

        if (popBackFragment != null) {
            navOptionsBuilder.setPopUpTo(popBackFragment, true)
        }

        val navOptions = navOptionsBuilder.build()
        navController.navigate(to, bundle, navOptions)
    }

    //this method actually setup the activities navgraph destination
    //just pass the starting fragment reference
    fun setupNavGrap1h(activity: FragmentActivity,containerViewId: Int,startFragment: Int){
        val navHostFragment =
            activity.supportFragmentManager.findFragmentById(containerViewId) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        val navInflater: NavInflater = navController.navInflater
        val navGraph: NavGraph = navInflater.inflate(R.navigation.navgraph)

        if (navGraph.findNode(startFragment) == null) {
            Log.e("Navigation", "Invalid destination: Fragment ID $startFragment is not in navgraph.xml")
            return
        }
        navGraph.setStartDestination(startFragment)

        navController.graph = navGraph
    }


















    fun setHomeDestination(homeDestination: Int?=null){
        this.homeDestination = homeDestination
        popBackStack.push(homeDestination)
    }

    fun setCurrentDestination(lastDestination: Int){
        this.CurrentDestination = lastDestination
    }
    fun getCurrentDestination(): Int? {
        return this.CurrentDestination
    }

   fun getNavController(activity: FragmentActivity,fragmentContainerView: Int): NavController?{
       val navHostFragment =  activity.supportFragmentManager.findFragmentById(fragmentContainerView) as? NavHostFragment
       return navHostFragment?.navController
   }

    //i use it for bottom Navigation.
    //


    fun setupNavGraph(activity: FragmentActivity, containerViewId: Int, destinationFragment: Int) {
        val navHostFragment = activity.supportFragmentManager.findFragmentById(containerViewId) as? NavHostFragment
        if (navHostFragment == null) {
            Log.e("Navigation", "NavHostFragment not found. Check containerViewId.")
            return
        }

        val navController: NavController = navHostFragment.navController

        // ✅ Ensure the destination exists in the navigation graph
        if (navController.graph.findNode(destinationFragment) == null) {
            Log.e("Navigation", "Invalid destination: Fragment ID $destinationFragment is not in navgraph.xml")
            return
        }



       // navController.popBackStack()


//        //remove current fragment local and main stack
//        if(popBackStack.peek()!= homeDestination){
//            popBackStack.pop()
//            navController.popBackStack()
//
//            if((popBackStack.peek()==homeDestination)){
                navController.popBackStack()
//            }
//        }
//
//
//        //add current fragment to local stack
//        if(destinationFragment!=homeDestination){
//            popBackStack.add(destinationFragment)
//        }

        // ✅ Apply animation
        val navOptions = NavOptions.Builder().build()
        navController.navigate(destinationFragment, null, navOptions)

        Log.wtf("Hello",popBackStack.size.toString())

    }

    fun navigateTo(navController: NavController?,destinationFragment: Int,bundle: Bundle?,homeState: Boolean?,popBack:Boolean?,exclusivePop: Boolean?){
        if(navController==null) return

        if(homeState==true) {
            navController.popBackStack(R.id.movieDashFragment, false)
        }
        else if(exclusivePop==true){
            navController.popBackStack(destinationFragment, true)
        }

        else if(popBack==true){
            navController.popBackStack()
        }

        navController.navigate(destinationFragment,null)

    }


}
