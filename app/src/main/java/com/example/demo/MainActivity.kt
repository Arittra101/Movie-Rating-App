    package com.example.demo

    import android.os.Bundle
    import androidx.activity.OnBackPressedCallback
    import androidx.activity.enableEdgeToEdge
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat
    import androidx.navigation.NavController
    import com.example.demo.databinding.ActivityMainBinding
    import com.example.demo.navigation.Navigation

    class MainActivity : AppCompatActivity() {
        private lateinit var binding: ActivityMainBinding
        private var currentPosition : Int = R.id.movieDashFragment
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

             val navController = Navigation.getNavController(this,R.id.fragmentContainerView)


            binding.cvHome.setOnClickListener {
                if(currentPosition!=R.id.movieDashFragment){
                    navigateTo(navController,R.id.movieDashFragment)
                }
            }
            binding.cvSearch.setOnClickListener {
                if(currentPosition!=R.id.searchkFragment2){
                    navigateTo(navController,R.id.searchkFragment2)
                }
            }
            binding.cvProfile.setOnClickListener {
                if(currentPosition!=R.id.profileFragment){
                    navigateTo(navController,R.id.profileFragment)
                }
            }



            onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {



                override fun handleOnBackPressed() {
                    when (navController?.currentDestination?.id) {
                        R.id.movieDashFragment -> {
                            finish()
                        }

                        R.id.searchkFragment2, R.id.profileFragment -> {
                            navController.navigate(R.id.movieDashFragment)
                            currentPosition = R.id.movieDashFragment
                        }

                        else -> {
                            navController?.popBackStack()
                        }
                    }
                }

            })

        }

        private fun navigateTo(navController: NavController?,destination: Int){

            if(navController==null) return

            if(currentPosition ==  R.id.movieDashFragment){
                Navigation.navigateTo(
                    navController,
                    destination,
                    bundle = null,
                    homeState = true,
                    popBack = false,
                    exclusivePop = false
                )
                currentPosition = destination
                return
            }

            if(destination==R.id.movieDashFragment){
                Navigation.navigateTo(
                    navController,
                    destination,
                    bundle = null,
                    homeState = false,
                    popBack = false,
                    exclusivePop = true
                )
                currentPosition = destination
                return
            }

            Navigation.navigateTo(
                navController = navController,
                destinationFragment = destination,
                bundle = null,
                homeState = false,
                popBack = true,
                exclusivePop = false
            )
            currentPosition = destination
        }
    }