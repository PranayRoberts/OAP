import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getCardById, updateCard } from "../../../services/NodeService";

function UpdateCard() {
  const [card_id, setCardId] = useState(0);
  const [cardName, setCardName] = useState("");
  const [card_type, setCardType] = useState("");

  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    getCardById(id).then((res) => {
      setCardId(res.data.card_id);
      setCardName(res.data.cardName);
      setCardType(res.data.card_type);
    });
  }, [id]);

  const handleUpdate = () => {
    const payload = {
      card_id: card_id,
      cardName: cardName,
      card_type: card_type,
    };

    updateCard(id, payload)
      .then((res) => navigate(-1))
      .then(alert("Details have been modified"))
      .catch((error) => console.log("Something went wrong"));
  };

  return (
    <div>
      <div>
        <label>Card ID:</label>
        <input
          type="number"
          id="card_id"
          name="card_id"
          value={card_id}
          disabled
        />
      </div>

      <div>
        <label>Card Name:</label>
        <input
          id="cardName"
          name="cardName"
          value={cardName}
          onChange={(e) => setCardName(e.target.value)}
        />
      </div>

      <div>
        <label>Card Type:</label>
        <input
          id="card_type"
          name="card_type"
          value={card_type}
          onChange={(e) => setCardType(e.target.value)}
        />
      </div>

      <div>
        <button class="btn btn-primary" onClick={handleUpdate}>
          Edit Card
        </button>
      </div>
    </div>
  );
}

export default UpdateCard;
