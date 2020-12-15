package com.example.mytrivia.ui.customs

import android.view.View
import androidx.fragment.app.Fragment

/**
 * Fragment base de la aplicación que implementa una interface de retroceso entre fragments
 * @author Axel Sanchez
 */
abstract class BaseFragment : Fragment() {
    /**
     * Extension Function que permite mostrar cualquier vista
     */
    fun View.show() { this.visibility = View.VISIBLE }

    /**
     * Extension Function que permite ocultar cualquier vista
     */
    fun View.hide() { this.visibility = View.GONE }

    /**
     * Extension Function que permite inhabilitar cualquier vista
     */
    fun View.disable() { this.isEnabled = false }

    /**
     * Extension Function que permite habilitar cualquier vista
     */
    fun View.enable() { this.isEnabled = true }
}