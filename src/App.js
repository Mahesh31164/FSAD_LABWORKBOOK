import React, { useState } from "react";
import AddStudent from "./AddStudent";
import StudentList from "./StudentList";

function App() {
  const [selected, setSelected] = useState(null);

  const refresh = () => {
    window.location.reload();
  };

  return (
    <div>
      <h1>Student Management System</h1>
      <AddStudent selected={selected} refresh={refresh} />
      <StudentList setSelected={setSelected} />
    </div>
  );
}

export default App;