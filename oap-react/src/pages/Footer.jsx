import React from 'react'
import '../styles/Footer.css'

function Footer() {
  
    let currentYear = new Date().getFullYear();
  
    return (
    <div data-testid="footer-1" className='footer'>
        <p> &copy; {currentYear} Optimized Amplifier Placement</p>
    </div>
  )
}

export default Footer