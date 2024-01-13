const months = [
  "Jan",
  "Feb",
  "Mar",
  "Apr",
  "May",
  "Jun",
  "Jul",
  "Aug",
  "Sep",
  "Oct",
  "Nov",
  "Dec",
];
export const formatDate = (time) => {
  if (!time) return "";
  const date = new Date(time);
  return (
    date.getDate() +
    " " +
    months[date.getMonth()] +
    " " +
    date.getHours() +
    ":" +
    date.getMinutes().toString().padStart(2, "0") +
    ":" +
    date.getSeconds().toString().padStart(2, "0")
  );
};
