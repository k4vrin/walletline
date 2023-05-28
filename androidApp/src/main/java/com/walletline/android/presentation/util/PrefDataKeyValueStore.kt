import android.content.Context
import androidx.core.content.edit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private const val USER_PREFERENCES_NAME = "user_preferences"
private const val USE_PATTERN_KEY = "use_pattern"
private const val PATTERN_KEY = "pattern"
private const val USE_FINGERPRINT_KEY = "use_fingerprint"

enum class SortOrder {
    NONE,
    BY_DEADLINE,
    BY_PRIORITY,
    BY_DEADLINE_AND_PRIORITY
}

/**
 * Class that handles saving and retrieving user preferences
 */
class UserPreferencesRepository private constructor(context: Context) {

    private val sharedPreferences =
        context.applicationContext.getSharedPreferences(USER_PREFERENCES_NAME, Context.MODE_PRIVATE)


    // Keep the sort order as a stream of changes
    private val _usePatternFlow = MutableStateFlow(usePattern)
    val usePatternFlow: StateFlow<Boolean> = _usePatternFlow

    private val _patternFlow = MutableStateFlow(pattern)
    val patternFlow: StateFlow<String> = _patternFlow
    /**
     * Get the sort order. By default, sort order is None.
     */
    private val usePattern: Boolean
        get() {
            return sharedPreferences.getBoolean(USE_PATTERN_KEY, false)
        }

    private val pattern: String
        get() {
            return sharedPreferences.getString(PATTERN_KEY, "")!!
        }
/*
    fun enableSortByDeadline(enable: Boolean) {
        val currentOrder = sortOrderFlow.value
        val newSortOrder =
            if (enable) {
                if (currentOrder == SortOrder.BY_PRIORITY) {
                    SortOrder.BY_DEADLINE_AND_PRIORITY
                } else {
                    SortOrder.BY_DEADLINE
                }
            } else {
                if (currentOrder == SortOrder.BY_DEADLINE_AND_PRIORITY) {
                    SortOrder.BY_PRIORITY
                } else {
                    SortOrder.NONE
                }
            }
        updateSortOrder(newSortOrder)
        _sortOrderFlow.value = newSortOrder
    }

    fun enableSortByPriority(enable: Boolean) {
        val currentOrder = sortOrderFlow.value
        val newSortOrder =
            if (enable) {
                if (currentOrder == SortOrder.BY_DEADLINE) {
                    SortOrder.BY_DEADLINE_AND_PRIORITY
                } else {
                    SortOrder.BY_PRIORITY
                }
            } else {
                if (currentOrder == SortOrder.BY_DEADLINE_AND_PRIORITY) {
                    SortOrder.BY_DEADLINE
                } else {
                    SortOrder.NONE
                }
            }
        updateSortOrder(newSortOrder)
        _sortOrderFlow.value = newSortOrder
    }*/

    fun enablePattern(enable:Boolean){
        updateUsePattern(enable)
    }
    fun savePattern(pattern:String){
        sharedPreferences.edit {
            putString(PATTERN_KEY, pattern)
        }
    }

    private fun updateUsePattern(usePattern: Boolean) {
        sharedPreferences.edit {
            putBoolean(USE_PATTERN_KEY, usePattern)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreferencesRepository? = null

        fun getInstance(context: Context): UserPreferencesRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let {
                    return it
                }

                val instance = UserPreferencesRepository(context)
                INSTANCE = instance
                instance
            }
        }
    }
}