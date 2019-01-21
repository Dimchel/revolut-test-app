package com.dimchel.revolut.features.converter.presentation

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dimchel.revolut.features.converter.models.RateModel
import java.math.BigDecimal
import java.util.*




interface ConverterListListener {

    fun onItemSelected(rateModel: RateModel)
    fun onInputValueChanged(inputValue: Double)

}

private const val SELECTED_ITEM_POSITION = 0

class ConverterAdapter(
    private val listener: ConverterListListener,
    private var inputValue: Double
) : RecyclerView.Adapter<ConverterViewHolder>() {

    private var ratesList: MutableList<RateModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ConverterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(com.dimchel.revolut.R.layout.item_rate, parent, false)

        return ConverterViewHolder(view)
    }

    override fun getItemCount(): Int = ratesList.size

    override fun onBindViewHolder(holder: ConverterViewHolder, position: Int) {
        holder.bind(ratesList[position], inputValue, listener)
    }

    fun updateRates(newRatesList: List<RateModel>) {
        newRatesList.forEach { newRate ->
            val rateIndexToUpdate: Int = ratesList.indexOfFirst { it.name == newRate.name }
            if (rateIndexToUpdate == -1) {
                ratesList = newRatesList.toMutableList()

                notifyDataSetChanged()
                return@forEach

            } else {
                ratesList[rateIndexToUpdate] = newRate

                notifyItemChanged(rateIndexToUpdate)
            }
        }
    }

    fun selectRate(rateModel: RateModel) {
        val selectedIndex = ratesList.indexOfFirst { it.name == rateModel.name }

        if (selectedIndex != SELECTED_ITEM_POSITION) {
            Collections.swap(ratesList, SELECTED_ITEM_POSITION, selectedIndex)

            notifyItemChanged(SELECTED_ITEM_POSITION)
            notifyItemChanged(selectedIndex)
        }
    }

    fun setInputValue(newInputValue: Double) {
        inputValue = newInputValue

        notifyItemRangeChanged(0, itemCount - 1)
    }
}

class ConverterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val currencyNameTextView: TextView = itemView.findViewById(com.dimchel.revolut.R.id.item_rate_currency_textview)
    private val currencyValueEditText: TextView = itemView.findViewById(com.dimchel.revolut.R.id.item_rate_value_edittext)

    private var inputValue: Double? = null
    private var listener: ConverterListListener? = null

    private var selectionPosition: Int? = null

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

        override fun afterTextChanged(text: Editable?) {
            if (adapterPosition == SELECTED_ITEM_POSITION) {
                if (inputValue != text.toString().toDouble()) {
                    listener!!.onInputValueChanged(text.toString().toDouble())
                }
            }
        }
    }

    fun bind(currentRateModel: RateModel, inputValue: Double, listener: ConverterListListener) {
        this.inputValue = inputValue
        this.listener = listener

        currencyValueEditText.removeTextChangedListener(textWatcher)

        currencyNameTextView.text = currentRateModel.name
        currencyValueEditText.text = multiplyValues(currentRateModel.value, inputValue)

        if (adapterPosition == SELECTED_ITEM_POSITION) {
            currencyValueEditText.addTextChangedListener(textWatcher)

        } else {
            currencyValueEditText.setOnFocusChangeListener { _, _ ->
                if (adapterPosition != SELECTED_ITEM_POSITION) {
                    listener.onItemSelected(currentRateModel)
                    listener.onInputValueChanged(currencyValueEditText.text.toString().toDouble())
                }
            }
        }

        itemView.setOnClickListener {
            if (adapterPosition != SELECTED_ITEM_POSITION) {
                listener.onItemSelected(currentRateModel)
                listener.onInputValueChanged(currencyValueEditText.text.toString().toDouble())
            }
        }
    }

    private fun multiplyValues(first: Double, second: Double): String =
        BigDecimal(first.toString()).multiply(BigDecimal(second.toString())).stripTrailingZeros().toPlainString()

}