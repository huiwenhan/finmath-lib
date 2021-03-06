/*
 * (c) Copyright Christian P. Fries, Germany. Contact: email@christian-fries.de.
 *
 * Created on 10.02.2004
 */
package net.finmath.montecarlo.interestrate.products;

import java.util.HashMap;
import java.util.Map;

import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.AbstractMonteCarloProduct;
import net.finmath.montecarlo.MonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.LIBORModelMonteCarloSimulationModel;
import net.finmath.montecarlo.process.component.factordrift.FactorDriftInterface;
import net.finmath.stochastic.RandomVariable;

/**
 * Base class for products requiring an LIBORModelMonteCarloSimulationModel as base class
 *
 * @author Christian Fries
 * @version 1.0
 */
public abstract class AbstractLIBORMonteCarloProduct extends AbstractMonteCarloProduct implements TermStructureMonteCarloProduct {

	/**
	 * @param currency The currency of this product (may be null for "any currency").
	 */
	public AbstractLIBORMonteCarloProduct(String currency) {
		super(currency);
	}

	/**
	 *
	 */
	public AbstractLIBORMonteCarloProduct() {
		super(null);
	}

	@Override
	public abstract RandomVariable getValue(double evaluationTime, LIBORModelMonteCarloSimulationModel model) throws CalculationException;

	public RandomVariable getValueForModifiedData(double evaluationTime, MonteCarloSimulationModel monteCarloSimulationModel, Map<String, Object> dataModified) throws CalculationException
	{
		return this.getValue(evaluationTime, monteCarloSimulationModel.getCloneWithModifiedData(dataModified));
	}

	@Override
	public Map<String, Object> getValues(double evaluationTime, LIBORModelMonteCarloSimulationModel model) throws CalculationException {
		RandomVariable value = getValue(evaluationTime, model);
		Map<String, Object> result = new HashMap<>();
		result.put("value", value.getAverage());
		result.put("error", value.getStandardError());
		return result;
	}

	@Override
	public RandomVariable getValue(double evaluationTime, MonteCarloSimulationModel model) throws CalculationException {
		// This product requires an LIBORModelMonteCarloSimulationModel model, otherwise there will be a class cast exception
		if(model instanceof LIBORModelMonteCarloSimulationModel) {
			return getValue(evaluationTime, (LIBORModelMonteCarloSimulationModel)model);
		}
		else {
			throw new IllegalArgumentException("The product " + this.getClass()
			+ " cannot be valued against a model " + model.getClass() + "."
			+ "It requires a model of type " + LIBORModelMonteCarloSimulationModel.class + ".");
		}
	}

	@Override
	public FactorDriftInterface getFactorDrift(LIBORModelMonteCarloSimulationModel referenceScheme, LIBORModelMonteCarloSimulationModel targetScheme) {
		throw new UnsupportedOperationException("Method not implemented.");
	}
}
