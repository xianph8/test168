package com.xian.common.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * 此文件内方法，均是反射实现。
 *
 * 慎用
 *
 * 如果需要非反射实现，可以使用 kotlin 语法糖 Class::Method 的方式来实现
 */
/*
//////////////////////// activity ////////////////////////////////
inline fun <reified VB : ViewBinding> BaseLoadingActivity.inflate() = lazy {
    inflateViewBinding<VB>(layoutInflater).apply {
        setContentView(root)
    }
}

inline fun <reified VB : ViewBinding> inflateViewBinding(layoutInflater: LayoutInflater) =
    VB::class.java.getMethod("inflate", LayoutInflater::class.java)
        .invoke(null, layoutInflater) as VB

//////////////////////// fragment & dialog & bottomSheet //////////////////////////
inline fun <reified VB : ViewBinding> BaseLoadingFragment.inflate() =
    FragmentViewBindingDelegate(VB::class.java)

inline fun <reified VB : ViewBinding> BaseDialogFragment.inflate() =
    FragmentViewBindingDelegate(VB::class.java)

inline fun <reified VB : ViewBinding> BaseBottomSheetFragment.inflate() =
    FragmentViewBindingDelegate(VB::class.java)

class FragmentViewBindingDelegate<VB : ViewBinding>(private val clazz: Class<VB>) :
    ReadOnlyProperty<Fragment, VB> {
    private var binding: VB? = null

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Fragment, property: KProperty<*>): VB {
        if (binding == null) {
            binding = clazz.getMethod("bind", View::class.java)
                .invoke(null, thisRef.requireView()) as VB
            thisRef.viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                override fun onDestroy(owner: LifecycleOwner) {
                    binding = null
                }
            })
        }
        return binding!!
    }
}*/

///////////////////////////////// view model///////////////////////////////////////
class ParamViewModelFactory<VM : ViewModel>(
    private val factory: () -> VM,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = factory() as T
}

inline fun <reified VM : ViewModel> AppCompatActivity.viewModel(
    noinline factory: () -> VM,
): Lazy<VM> = viewModels { ParamViewModelFactory(factory) }

//////////////////////////// non reflection ////////////////////////////////////

fun <VB : ViewBinding> AppCompatActivity.inflate(
    inflater: (LayoutInflater) -> VB
) = lazy { inflater(layoutInflater).apply { setContentView(root) } }

fun <VB : ViewBinding> Fragment.inflate(
    inflater: (LayoutInflater, ViewGroup?, Boolean) -> VB,
    container: ViewGroup?
) = lazy {
    var binding: VB? = inflater(layoutInflater, container, false)
    doOnDestroyView { binding = null }
    binding!!
}

/**
 * 仅适用于，已经设置过 ContentView 的
 */
fun <VB : ViewBinding> Activity.bind(
    bind: (View) -> VB
) = lazy {
    bind(findViewById(android.R.id.content))
}

fun <VB : ViewBinding> Fragment.bind(
    bind: (View) -> VB
) = lazy {
    var binding: VB? = bind(requireView())
    doOnDestroyView { binding = null }
    binding!!
}

inline fun Fragment.doOnDestroyView(crossinline block: () -> Unit) =
    viewLifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroyView() {
            block.invoke()
        }
    })

//////////////////////// 这个代理类是自己实现的，未完整//////////////////////////
class ViewBindingDelegate<VB : ViewBinding>(
    private val inflate: (LayoutInflater) -> VB,
    private val layoutInflater: LayoutInflater
) : ReadOnlyProperty<Fragment, VB> {

    private var _binding: VB? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): VB {
        if (_binding == null) {
            _binding = inflate(layoutInflater)
            thisRef.viewLifecycleOwner.lifecycle.addObserver(object : LifecycleEventObserver {
                override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                    if (event == Lifecycle.Event.ON_DESTROY) {
                        _binding = null
                    }
                }

            })
        }
        return _binding!!
    }

}

