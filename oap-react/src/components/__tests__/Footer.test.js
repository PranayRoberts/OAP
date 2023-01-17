import { render, screen, cleanup } from "@testing-library/react";
import Footer from '../../pages/Footer.jsx'

test("should render Footer", () => {
  render(<Footer/>);
  const footerElement = screen.getByTestId("footer-1");
  expect(footerElement).toHaveTextContent('Optimized Amplifier Placement');
});
