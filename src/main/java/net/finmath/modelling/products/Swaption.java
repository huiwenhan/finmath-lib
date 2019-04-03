/*
 * (c) Copyright Christian P. Fries, Germany. Contact: email@christian-fries.de.
 *
 * Created on 31.03.2019
 */

package net.finmath.modelling.products;

/**
 * A market interface for all swaption implementations and a holder for some product specific definitions.
 * 
 * @author Christian Fries
 */
public interface Swaption {

	/**
	 * Swaptions specific value units, like swaption implied volatilities.
	 * 
	 * @author Christian Fries
	 */
	public enum ValueUnit {
		/** Returns the value of the swaption **/
		VALUE,
		/** Returns the Black-Scholes implied integrated variance, i.e., <i>&sigma;<sup>2</sup> T</i> **/
		INTEGRATEDVARIANCELOGNORMAL,
		/** Returns the Black-Scholes implied volatility, i.e., <i>&sigma;</i> **/
		VOLATILITYLOGNORMAL,
		/** Returns the Bachelier implied integrated variance, i.e., <i>&sigma;<sup>2</sup> T</i> **/
		INTEGRATEDVARIANCENORMAL,
		/** Returns the Bachelier implied volatility, i.e., <i>&sigma;</i> **/
		VOLATILITYNORMAL
	}
}
