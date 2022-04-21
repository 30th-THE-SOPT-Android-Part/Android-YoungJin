package org.sopt.soptseminar.models.types

import org.sopt.soptseminar.R

enum class SoptPartType(val strRes: Int) {
    AOS(R.string.part_aos),
    IOS(R.string.part_ios),
    WEB(R.string.part_web),
    SERVER(R.string.part_server),
    DESIGN(R.string.part_design),
    PLAN(R.string.part_plan)
}