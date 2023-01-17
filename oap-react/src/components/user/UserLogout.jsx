import React from 'react'
import { useNavigate } from 'react-router-dom'

function UserLogout() {
  const navigate = useNavigate()
  const user= JSON.parse(localStorage.getItem("loggedInUser"))

  const handleLogout = () => {
    if(user!==null){
        localStorage.removeItem("loggedInUser")
        alert(user + " has been logged out!")
        navigate("/")
    }
  }
  
      return (
    <div>
        <button onClick={handleLogout} class='nav-link'>Logout</button>
    </div>
  )
}

export default UserLogout