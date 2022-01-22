import React from 'react';
import background from '../../pictures/blueBackground.jpeg';
  
const HomePageClient = () => {
    return (
        <div style={{backgroundImage: `url(${background})` , height: "689px",
                backgroundSize: "cover",
                backgroundRepeat: "no-repeat",
                backgroundPosition: "center",}}>
                    <br/><br/><br/>
            <p style={{fontFamily:"Verdana", fontStyle:"bold", fontSize:"50px"}}>Welcome to Booking entites Home page for Clients!</p>
        </div>
    )
};

export default HomePageClient;