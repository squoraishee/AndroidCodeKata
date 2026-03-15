package com.kata.testbed.sections

import com.kata.testbed.sections.s01_kotlin_basics.registerKotlinBasics
import com.kata.testbed.sections.s02_compose_ui.registerComposeUi
import com.kata.testbed.sections.s03_state_management.registerStateManagement
import com.kata.testbed.sections.s04_navigation.registerNavigation
import com.kata.testbed.sections.s05_lists_layouts.registerListsLayouts
import com.kata.testbed.sections.s06_coroutines_flows.registerCoroutinesFlows
import com.kata.testbed.sections.s07_dependency_injection.registerDependencyInjection
import com.kata.testbed.sections.s08_room_persistence.registerRoomPersistence
import com.kata.testbed.sections.s09_clean_architecture.registerCleanArchitecture
import com.kata.testbed.sections.s10_testing_advanced.registerTestingAdvanced

private var registered = false

fun registerAllSections() {
    if (registered) return
    registered = true
    registerKotlinBasics()
    registerComposeUi()
    registerStateManagement()
    registerNavigation()
    registerListsLayouts()
    registerCoroutinesFlows()
    registerDependencyInjection()
    registerRoomPersistence()
    registerCleanArchitecture()
    registerTestingAdvanced()
}
